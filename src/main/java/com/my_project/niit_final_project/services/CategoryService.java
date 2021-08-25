package com.my_project.niit_final_project.services;

import com.my_project.niit_final_project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public  Long countTotal(){
        return categoryRepository.count();
    }
}
