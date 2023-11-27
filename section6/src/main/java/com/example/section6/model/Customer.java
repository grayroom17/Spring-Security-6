package com.example.section6.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String email;

    String mobileNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String pwd;

    @Enumerated(EnumType.STRING)
    Role role;

    Date createdAt;

}
