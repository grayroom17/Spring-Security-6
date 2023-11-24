package com.example.section4.controller.dto;

import com.example.section4.model.Role;


public record CustomerCreateDto(String email,
                                String password,
                                Role role) {

}
