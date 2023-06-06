package com.example.javabootcamphw28.Repository;


import com.example.javabootcamphw28.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    Product findProductById(Integer id);
}
