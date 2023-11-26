package com.example.section6.controller.dto;

import com.example.section6.model.Role;


public record CustomerReadDto(Long id,
                              String email,
                              String password,
                              Role role) {

}
