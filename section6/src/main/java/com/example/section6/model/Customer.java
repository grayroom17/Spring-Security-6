package com.example.section6.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 45)
    String email;

    @Column(length = 200)
    String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    Role role;

}
