package com.example.section7.controller.dto;

import com.example.section7.model.Role;


public record CustomerCreateDto(String name,
                                String email,
                                String mobileNumber,
                                String password,
                                Role role) {

}
