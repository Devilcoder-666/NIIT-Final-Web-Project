package com.my_project.niit_final_project.services;

import com.my_project.niit_final_project.entities.Order;
import com.my_project.niit_final_project.entities.User;
import com.my_project.niit_final_project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public  Long countTotal(){
        return orderRepository.count();
    }
    public boolean save(Order order) {

        LocalDateTime localDate = LocalDateTime.now();
        /*  user.setPassword(helper.getMD5(user.getPassword()));*/
        order.setOrderDate(localDate);

        try {
            orderRepository.save(order);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean delete(long id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Order getOrderById(long id) {
        return orderRepository.findById(id).get();
    }

    public Page<Order> getPageUser(int page ){
        Pageable pageable=  PageRequest.of(page,10);
        return orderRepository.findAll(pageable);
    }
    public  Iterable<Order> getAll(){

        return orderRepository.findAll();
    }
}
