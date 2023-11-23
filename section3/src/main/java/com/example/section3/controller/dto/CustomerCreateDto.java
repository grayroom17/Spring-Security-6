package com.example.section3.controller.dto;

import com.example.section3.model.Role;


public record CustomerCreateDto(String email,
                                String password,
                                Role role) {

}
