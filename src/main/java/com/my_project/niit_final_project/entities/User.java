package com.my_project.niit_final_project.entities;


import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name ="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @Column(name = "email",length=255,nullable = false)
    String email;
    @Column(name = "password",length = 255,nullable = false)
    String password;
    @Column(name="full_name",length = 255,nullable = false)
    String fullName;
    @Column(name="phone_number",length=20)
    String phoneNumber;
    @Column(name="address",length=100)
    String address;
    @Column(name ="created_date",nullable = false)
    LocalDateTime createdDate;

    @Column(name="role",nullable = false)
    Integer role;
}
