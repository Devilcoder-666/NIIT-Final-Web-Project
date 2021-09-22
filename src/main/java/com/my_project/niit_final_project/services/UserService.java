package com.my_project.niit_final_project.services;

import com.my_project.niit_final_project.entities.User;
import com.my_project.niit_final_project.helpers.Helper;
import com.my_project.niit_final_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.my_project.niit_final_project.configure.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;


@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Helper helper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    public boolean save(User user) {

        LocalDateTime localDate = LocalDateTime.now();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
      /*  user.setPassword(helper.getMD5(user.getPassword()));*/
        user.setCreatedDate(localDate);

        try {
            userRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean delete(long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    public Page<User> getPageUser(int page ){
        Pageable pageable=  PageRequest.of(page,10);
        return userRepository.findAll(pageable);
    }
    public  Iterable<User> getAll(){

        return userRepository.findAll();
    }
    public  Long countTotal(){
        return userRepository.count();
    }
}
