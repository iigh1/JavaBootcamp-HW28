package com.example.javabootcamphw28.Controller;


import com.example.javabootcamphw28.Model.MyOrder;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Service.OrderService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;



    @GetMapping("/get")
    public ResponseEntity getOrders(@AuthenticationPrincipal MyUser myUser){
       List<MyOrder> myOrders  =orderService.getOrders(myUser);
        return ResponseEntity.status(200).body(myOrders);
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity addOrder(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer productId,@RequestBody MyOrder myOrder){
        orderService.addOrder(myUser.getId(),productId,myOrder);
        return ResponseEntity.status(200).body("Order added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal MyUser myUser,@RequestBody MyOrder myOrder,@PathVariable Integer id){
        orderService.updateOrder(myUser.getId(),myOrder,id);
        return ResponseEntity.status(200).body("order updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        orderService.deleteOrder(myUser,id);
        return ResponseEntity.status(200).body("order deleted");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getOrderById(@AuthenticationPrincipal MyUser myUser, Integer id){
        MyOrder myOrder= orderService.getOrderById(myUser,id);
        return ResponseEntity.status(200).body(myOrder);
    }
    @PutMapping("/assign/{orderId}/{productId}")
    public ResponseEntity assignProductToOrder(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer orderId, @PathVariable Integer productId){
        orderService.assignProductToOrder(myUser.getId(), orderId, productId);
        return ResponseEntity.status(200).body("assigned done");
    }
    @GetMapping("/get/{id}")
    public ResponseEntity getOrder(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer id){
        return ResponseEntity.status(200).body(orderService.getOrder(myUser.getId(), id));
    }

    @PutMapping("/status/{orderId}/{status}")
    public ResponseEntity changeStatus(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer orderId,@PathVariable String status){
        orderService.changStatus(myUser.getId(),orderId,status);
        return ResponseEntity.status(200).body("status changed");

    }
}
