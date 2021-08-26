package com.my_project.niit_final_project.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name",length = 255)
    String name;

    @Column(name = "price")
    Double price;

    @Column(name = "description",length = 10000)
    String description;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "discount")
    Double discount;

    @Column(name = "status")
    Integer status;

    @Column(name = "picture",length = 1000)
    String picture;

    @Column(name = "category_id")
    long categoryId;

    @ManyToOne()
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    Category category;
    @Column(name ="created_date",nullable = false)
    LocalDateTime createdDate;
}
