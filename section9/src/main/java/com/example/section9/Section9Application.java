package com.example.section9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class Section9Application {

    public static void main(String[] args) {
        SpringApplication.run(Section9Application.class, args);
    }

}
