package com.example.section6.controller.dto;

import com.example.section6.model.Role;


public record CustomerCreateDto(String name,
                                String email,
                                String mobileNumber,
                                String password,
                                Role role) {

}
