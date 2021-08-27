package com.my_project.niit_final_project.services;


import com.my_project.niit_final_project.entities.Product;
import com.my_project.niit_final_project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadService uploadService;

    public Long countTotal() {
        return productRepository.count();
    }


    public boolean save(Product product, MultipartFile uploadImage) {
        LocalDateTime localDate = LocalDateTime.now();

        product.setCreatedDate(localDate);
        try {
            //upload ảnh
            if (uploadImage != null) {
                //tiến hành upload
                String uploadPath = uploadService.upload(uploadImage);
                if (uploadPath != null) {
                    product.setPicture(uploadPath);
                } else {
                    return false;
                }
            }
            productRepository.save(product);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delete(long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).get();
    }

    public Page<Product> getPageProduct(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return productRepository.findAll(pageable);
    }

    public Page<Product> getPageSaleProduct(int page) {
        Pageable pageable = PageRequest.of(page, 4, Sort.by("discount").descending());
        return productRepository.findAll(pageable);
    }

    public Page<Product> getPageNewProduct(int page) {
        Pageable pageable = PageRequest.of(page, 4, Sort.by("id").descending());
        return productRepository.findAll(pageable);
    }


}

