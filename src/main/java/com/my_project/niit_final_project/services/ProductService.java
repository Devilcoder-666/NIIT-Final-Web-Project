package com.my_project.niit_final_project.services;

import com.my_project.niit_final_project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public  Long countTotal(){
        return productRepository.count();
    }






}
