package com.sitesquad.ministore.config;

import com.sitesquad.ministore.exception.AccessDeniedException;
import com.sitesquad.ministore.model.RequestMeta;
import com.sitesquad.ministore.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@CrossOrigin
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;

    private RequestMeta requestMeta;

    public JwtInterceptor(RequestMeta requestMeta){
        this.requestMeta=requestMeta;
    }


    @CrossOrigin
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            System.out.println("URI: " + request.getRequestURI());

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, X-Forwarded-For");
            response.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Vary", "Origin, Access-Control-Request-Method, Access-Control-Request-Headers");


        if (isLoginApi(request)) {
            return true;
        }

        try {


            String auth = request.getHeader("Authorization");

            Claims claims = jwtUtils.verify(auth);
            requestMeta.setUserId(Long.valueOf(claims.getIssuer()));
            requestMeta.setName(claims.get("name").toString());
            requestMeta.setRole(claims.get("role").toString());
            requestMeta.setEmail(claims.get("email").toString());

            System.out.println("Name: "+ requestMeta.getName());
            System.out.println("Role: "+ requestMeta.getRole());
            System.out.println("Email: "+requestMeta.getUserId());
            System.out.println("PRE-HANDLE");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new AccessDeniedException("Access denied");
        }
    }

    private boolean isLoginApi(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.contains("/login") || requestURI.contains("/home") || requestURI.contains("/error");
    }
}
