package com.my_project.niit_final_project.services;

import com.my_project.niit_final_project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public  Long countTotal(){
        return orderRepository.count();
    }
}
