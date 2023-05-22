/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sitesquad.ministore.controller;

import com.sitesquad.ministore.model.ResponseObject;
import com.sitesquad.ministore.model.Role;
import com.sitesquad.ministore.repository.RoleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping(path = "/role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;
    
    @GetMapping()
    public ResponseEntity<ResponseObject> getAllRole(){
        List<Role> roleList = roleRepository.findAll();
        if(!roleList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Role List found", roleList)
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(404, "Role List not found", roleList)
            );
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getRoleById(Long id){
        Optional<Role> foundRole = roleRepository.findById(id);
        if(!foundRole.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Role found", foundRole)
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(404, "Role not found", foundRole)
            );
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addProduct(@RequestBody Role role){
        Role addRole = roleRepository.save(role);
        if(addRole != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Add sucessfully ", addRole)
            );
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(500, "Add failed ", addRole)
            );
        }
    }
    
    
}