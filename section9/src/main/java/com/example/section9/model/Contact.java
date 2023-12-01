package com.example.section9.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "contact_message")
public class Contact {

    @Id
    String id;

    String contactName;

    String contactEmail;

    String subject;

    String message;

    Date createdAt;

}
