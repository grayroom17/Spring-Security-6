package com.example.section6.controller.dto;

import com.example.section6.model.Role;


public record CustomerCreateDto(String email,
                                String password,
                                Role role) {

}
