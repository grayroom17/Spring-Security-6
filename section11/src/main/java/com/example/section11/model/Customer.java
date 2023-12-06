package com.example.section11.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

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
    @Column(name = "pwd")
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    Date createdAt;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "customer", fetch = EAGER)
    Set<Authority> authorities;

}
