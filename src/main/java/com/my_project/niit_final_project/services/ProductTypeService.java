package com.my_project.niit_final_project.services;

import com.my_project.niit_final_project.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;
    public  Long countTotal(){
        return productTypeRepository.count();
    }
}
