package com.example.javabootcamphw28.Service;


import com.example.javabootcamphw28.ApiException.ApiException;
import com.example.javabootcamphw28.Model.MyOrder;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Model.Product;
import com.example.javabootcamphw28.Repository.AuthRepository;
import com.example.javabootcamphw28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private  final AuthRepository authRepository;

    public List<Product> getProducts(MyUser myUser){
       return productRepository.findAll();
    }

    public void addProduct(Product product){

        productRepository.save(product);
    }

    public void update(Integer userId, Product product, Integer productId){
        Product product1= productRepository.findProductById(productId);
        MyUser myUser= authRepository.findMyUserById(userId);
        if (product1==null){
            throw new ApiException("not found");
        }
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());

        productRepository.save(product1);
    }

    public void delete(Integer productId){
        Product product= productRepository.findProductById(productId);
        if (product==null){
            throw new ApiException("not found");
        }

        productRepository.delete(product);
    }

    public Product getProductById(MyUser myUser, Integer id){
        Product product= productRepository.findProductById(id);

        if (product==null){
            throw  new ApiException("Product not found");
        }
        return product;
    }


}
