package com.my_project.niit_final_project.repositories;

import com.my_project.niit_final_project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
