package com.my_project.niit_final_project.services;


import com.my_project.niit_final_project.entities.Category;
import com.my_project.niit_final_project.entities.Product;
import com.my_project.niit_final_project.entities.ProductType;
import com.my_project.niit_final_project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private ProductTypeService productTypeService;



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

    public Page<Product> getPageProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
   public ArrayList<Product> getSuggestProductList(){
        ArrayList<Product> suggestProductList=new ArrayList<Product>();
        for(ProductType productType   : productTypeService.getAll()){
            for(Category category : productType.getCategories()){

                            int rnd = new Random().nextInt(category.getProducts().size());
                           List<Product> demoListProduct= new ArrayList<Product>();
                           for(Product product : category.getProducts()){
                               demoListProduct.add(product);
                           }
                           suggestProductList.add(demoListProduct.get(rnd));
            }
        }
        return suggestProductList;
    }
    public Page<Product> getPageSaleProduct(int page,int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("discount").descending());
        return productRepository.findAll(pageable);
    }

    public Page<Product> getPageNewProduct(int page,int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return productRepository.findAll(pageable);
    }

    public Page<Product> getPageSearchProduct(int page,int size,String keywords){
        Pageable pageable=PageRequest.of(page, size);
        return productRepository.searchProducts(keywords,pageable);
    }

    public Page<Product> getPageProductByCategoryId(int page,int size,long id){
        Pageable pageable= PageRequest.of(page, size);
      Page<Product> pageProduct= productRepository.findByCategoryId(id,pageable);
      return pageProduct;
    }


}

