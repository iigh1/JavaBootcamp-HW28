package com.example.javabootcamphw28.Repository;


import com.example.javabootcamphw28.Model.MyOrder;
import com.example.javabootcamphw28.Model.MyUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<MyOrder, Integer> {

    List<MyOrder> findAllByMyUser(MyUser myUser);

    MyOrder findMyOrderById(Integer id);


}
