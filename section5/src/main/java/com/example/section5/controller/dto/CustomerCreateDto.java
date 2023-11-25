package com.example.section5.controller.dto;

import com.example.section5.model.Role;


public record CustomerCreateDto(String email,
                                String password,
                                Role role) {

}
