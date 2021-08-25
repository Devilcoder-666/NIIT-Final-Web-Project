package com.my_project.niit_final_project.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "status")
    Long status;

    @Column(name = "product_type_id")
    Long typeId;

    @ManyToOne()
    @JoinColumn(name = "product_type_id", insertable = false, updatable = false)
    ProductType productType;



    @OneToMany(mappedBy="category",fetch = FetchType.LAZY)
    Collection<Product> products;
}
