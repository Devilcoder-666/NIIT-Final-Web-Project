package com.my_project.niit_final_project.entities;

import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@Table(name="product_types")

public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "status")
    Integer status;

    @OneToMany(mappedBy="productType",fetch = FetchType.LAZY)
    Collection<Category> categories;
    @Column(name ="created_date",nullable = false)
    LocalDateTime createdDate;
}
