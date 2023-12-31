package com.sitesquad.ministore.service;

import com.sitesquad.ministore.model.Order;
import com.sitesquad.ministore.repository.OrderRepository;
import com.sitesquad.ministore.repository.UserRepository;
import com.sitesquad.ministore.repository.VoucherRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author admin
 */
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoucherRepository voucherRepository;

    public List<Order> findAll() {
        return orderRepository.findByIsDeletedNullOrIsDeletedFalse();
    }

    public Order findById(Long id) {
        List<Order> orderList = orderRepository.findByIsDeletedNullOrIsDeletedFalse();
        Order foundOrder = new Order();
        for(Order order: orderList) {
            if(order.getOrderId().equals(id)) {
                foundOrder = order;
            }
        }
        return foundOrder;
    }

    public List<Order> findByDate(int month) {
        List<Order> orderList = orderRepository.findByIsDeletedNullOrIsDeletedFalse();
        List<Order> orderInAMonth = new ArrayList<>();
        for (Order order : orderList) {
            //extract date in timestamp to month
            long timestamp = order.getDate().getTime();
            Date date = new Date(timestamp);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int time = cal.get(Calendar.MONTH) + 1;
            if (time == month) {
                orderInAMonth.add(order);
            }
        }
        return orderInAMonth;
    }

    public List<Map<String, Object>> findMostSoldHour() {
        return orderRepository.findMostSoldHour();
    }


    public Order add(Order order) {
        order.setOrderUser(userRepository.findById(order.getUserId()).get());
        if (order.getVoucherId() != null) {
            order.setVoucher(voucherRepository.findById(order.getVoucherId()).get());
        }
        return orderRepository.save(order);
    }

    public Order edit(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    public boolean delete(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return false;
        } else {
            order.setIsDeleted(true);
            orderRepository.save(order);
            return true;
        }
    }
}
