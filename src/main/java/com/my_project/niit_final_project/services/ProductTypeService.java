package com.my_project.niit_final_project.services;

import com.my_project.niit_final_project.entities.ProductType;

import com.my_project.niit_final_project.repositories.ProductTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;
    public  Long countTotal(){
        return productTypeRepository.count();
    }

    public boolean save(ProductType productType) {
        LocalDateTime localDate = LocalDateTime.now();

        productType.setCreatedDate(localDate);
        try {
            productTypeRepository.save(productType);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean delete(long id) {
        try {
            productTypeRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ProductType getProductTypeById(long id) {
        return productTypeRepository.findById(id).get();
    }

    public Page<ProductType> getPageProductType(int page,int size ){
        Pageable pageable=  PageRequest.of(page,size);
        return productTypeRepository.findAll(pageable);
    }
    public  Iterable<ProductType> getAll(){

        return productTypeRepository.findAll();
    }

}
