    package com.sitesquad.ministore.controller.order;

import com.sitesquad.ministore.model.OrderDetails;
import com.sitesquad.ministore.dto.ResponseObject;
import com.sitesquad.ministore.service.OrderDetailsService;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

    /**
 *
 * @author admin
 */
@RestController
public class OrderDetailsController {
    
    @Autowired
    OrderDetailsService orderDetailsService;

    @GetMapping("/orderDetail")
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsService.findAll();
    }

    @GetMapping("/orderDetail/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        OrderDetails foundOrderDetail = orderDetailsService.findById(id);
        if (foundOrderDetail != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Found OrderDetail id = " + id, foundOrderDetail)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(404, "Cant find OrderDetail id = " + id, "")
            );
        }
    }

    @GetMapping("/orderDetail/searchByOrderId")
    public ResponseEntity<ResponseObject> findByOrderId(@RequestParam Long id) {
        List<OrderDetails> foundOrderDetail = orderDetailsService.findByOrderId(id);
//        List<Order> foundOrders = orderRepository.findByUserIdAndName(id, name);
        if (!foundOrderDetail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Found OrderDetails ", foundOrderDetail)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(404, "Cant find any OrderDetails matched", "")
            );
        }
    }
        
//    @GetMapping("/orderDetail/search/product")
//    public ResponseEntity<ResponseObject> findByProductId(@RequestParam Long id) {
//        List<OrderDetails> foundOrders = orderDetailRepository.findByProductId(id);
////        List<Order> foundOrders = orderRepository.findByUserIdAndName(id, name);
//        if (!foundOrders.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject(200, "Found OrderDetails ", foundOrders)
//            );
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject(404, "Cant find any OrderDetails matched", "")
//            );
//        }
//    }
    
//    @GetMapping("/orderDetail/search/productvoucher")
//    public ResponseEntity<ResponseObject> findByProductVoucherId(@RequestParam Long id) {
//        List<OrderDetails> foundOrders = orderDetailRepository.findByProductVoucherId(id);
////        List<Order> foundOrders = orderRepository.findByUserIdAndName(id, name);
//        if (!foundOrders.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject(200, "Found OrderDetails ", foundOrders)
//            );
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject(404, "Cant find any OrderDetails matched", "")
//            );
//        }
//    }

    @PostMapping("/orderDetail")
    public ResponseEntity<ResponseObject> createOrderDetail(@RequestBody OrderDetails orderDetail) {
        OrderDetails addOrderDetail = orderDetailsService.add(orderDetail);
        if (addOrderDetail != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Add sucessfully ", addOrderDetail)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(500, "Cant add OrderDetail", addOrderDetail)
            );
        }
    }

//    @PutMapping("/orderDetail")
//    public ResponseEntity<ResponseObject> editOrder(@RequestBody OrderDetails orderDetail) {
//        orderDetail.setOrderDet(orderRepository.findById(orderDetail.getOrderId()).get());
//        orderDetail.setProduct(productRepository.findById(orderDetail.getProductId()).get());
//        orderDetail.setProductVoucher(productVoucherRepository.findById(orderDetail.getProductVoucherId()).get());
//        OrderDetails addOrderDetail = orderDetailRepository.save(orderDetail);
//        if (addOrderDetail != null) {
////            orderRepository.findById(order.getId()).get().setIsDeleted(true);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject(200, "Edit sucessfully ", addOrderDetail)
//            );
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject(500, "Cant edit OrderDetail", orderDetail)
//            );
//        }
//
//    }
}
