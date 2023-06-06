package com.example.javabootcamphw28.Controller;


import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        List<MyUser> myUserList=authService.getUsers();
        return ResponseEntity.status(200).body(myUserList);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser){
        authService.register(myUser);
        return ResponseEntity.status(200).body("user register");
    }

    @PostMapping("/customer")
    public ResponseEntity user(){
        return ResponseEntity.status(200).body("welcome customer");
    }

    @PostMapping("/admin")
    public ResponseEntity admin(){

        return ResponseEntity.status(200).body("welcome admin");
    }

    @PostMapping("/login")
    public ResponseEntity login(){

        return ResponseEntity.status(200).body("login");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){

        return ResponseEntity.status(200).body("logout");
    }


}

