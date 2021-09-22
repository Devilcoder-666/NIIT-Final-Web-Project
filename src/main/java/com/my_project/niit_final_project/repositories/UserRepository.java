package com.my_project.niit_final_project.repositories;

import com.my_project.niit_final_project.entities.Order;
import com.my_project.niit_final_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
