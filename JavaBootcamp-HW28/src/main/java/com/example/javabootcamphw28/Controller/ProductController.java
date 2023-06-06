package com.example.javabootcamphw28.Controller;


import com.example.javabootcamphw28.Model.MyOrder;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Model.Product;
import com.example.javabootcamphw28.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity getProducts(@AuthenticationPrincipal MyUser myUser){

        List<Product> products= productService.getProducts(myUser);
        return ResponseEntity.status(200).body(products);
    }
    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal MyUser myUser,@RequestBody Product product,@PathVariable Integer id){
        productService.update(myUser.getId(), product,id);
        return ResponseEntity.status(200).body("Product updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        productService.delete(id);
        return ResponseEntity.status(200).body("order deleted");
    }
    @GetMapping("/get-id/{id}")
    public ResponseEntity getProductById(@AuthenticationPrincipal MyUser myUser, Integer id){
        Product product= productService.getProductById(myUser,id);
        return ResponseEntity.status(200).body(product);
    }
}
