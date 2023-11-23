package com.example.section3.controller.dto;

import com.example.section3.model.Role;


public record CustomerReadDto(Long id,
                              String email,
                              String password,
                              Role role) {

}
