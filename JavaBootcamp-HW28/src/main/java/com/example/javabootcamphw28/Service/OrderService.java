package com.example.javabootcamphw28.Service;


import com.example.javabootcamphw28.ApiException.ApiException;
import com.example.javabootcamphw28.Model.MyOrder;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Model.Product;
import com.example.javabootcamphw28.Repository.AuthRepository;
import com.example.javabootcamphw28.Repository.OrderRepository;
import com.example.javabootcamphw28.Repository.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AuthRepository authRepository;
    private final ProductRepository productRepository;

    public List<MyOrder> getOrders(MyUser myUser){

        return orderRepository.findAllByMyUser(myUser);
    }

    public void addOrder(Integer productId,Integer userId, MyOrder myOrder){

        MyUser myUser1= authRepository.findMyUserById(userId);
        Product product =productRepository.findProductById(productId);

        if (myUser1 ==null || product==null){
            throw new ApiException("not found");
        }
        myOrder.setTotalPrice(product.getPrice()*myOrder.getQuantity());
        myOrder.setStatus("new");
        myOrder.setProduct(product);
        myOrder.setMyUser(myUser1);

        orderRepository.save(myOrder);
     }

    public void updateOrder(Integer userId, MyOrder myOrder,Integer id){

        MyUser myUser = authRepository.findMyUserById(userId);
        MyOrder myOrder1= orderRepository.findMyOrderById(id);
        if (myOrder1==null || userId != myOrder1.getMyUser().getId()){
            throw  new ApiException("order not found");
        }

//        myOrder1.setMyUser(myOrder.getMyUser());
//        myOrder1.setProduct(myOrder.getProduct());
        myOrder1.setStatus(myOrder.getStatus());
        myOrder1.setQuantity(myOrder.getQuantity());
        myOrder1.setDateReceived(myOrder.getDateReceived());
        myOrder1.setTotalPrice(myOrder.getTotalPrice());

        orderRepository.save(myOrder1);

    }

    public void deleteOrder(MyUser myUser,Integer id){

        MyOrder myOrder= orderRepository.findMyOrderById(id);
        if (myOrder==null || myOrder.getStatus().equals("inProgress")){
            throw  new ApiException("can't deleted");
        }
        orderRepository.delete(myOrder);
    }

    public MyOrder getOrderById(MyUser myUser, Integer id){
        MyOrder myOrder=orderRepository.findMyOrderById(id);

        if (myOrder==null){
            throw  new ApiException("Order not found");
        }
        return myOrder;
    }
    public void assignProductToOrder(Integer userId, Integer orderId, Integer productId){
        MyUser user = authRepository.findMyUserById(userId);
        MyOrder myOrder= orderRepository.findMyOrderById(orderId);
        Product product = productRepository.findProductById(productId);
        if(user == null ||(userId != myOrder.getMyUser().getId() && user.getRole().equalsIgnoreCase("customer")))
            throw new ApiException("Invalid");
        myOrder.setProduct(product);
        myOrder.setTotalPrice(product.getPrice()*myOrder.getQuantity());
        orderRepository.save(myOrder);
    }


    public void changStatus(Integer userId,Integer orderId, String status){
        MyOrder myOrder=orderRepository.findMyOrderById(orderId);
        MyUser myUser=authRepository.findMyUserById(userId);

        if (myOrder==null || myUser==null || myUser.getRole().equalsIgnoreCase("customer")){
            throw new ApiException("not found");
        }

            myOrder.setStatus(status);

        orderRepository.save(myOrder);
    }

























}
