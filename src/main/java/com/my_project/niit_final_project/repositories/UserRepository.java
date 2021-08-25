package com.my_project.niit_final_project.repositories;

import com.my_project.niit_final_project.entities.Order;
import com.my_project.niit_final_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
