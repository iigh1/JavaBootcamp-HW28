package com.example.javabootcamphw28.Service;


import com.example.javabootcamphw28.ApiException.ApiException;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


    public List<MyUser> getUsers(){
        return authRepository.findAll();
    }

    public void register(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("CUSTOMER");
        authRepository.save(myUser);
    }

    public MyUser getMyUserById(MyUser myUser, Integer id){
        MyUser myUser1=authRepository.findMyUserById(id);
        if (myUser1==null){
            throw new ApiException("not found");
        }
        return myUser1;

    }
}
