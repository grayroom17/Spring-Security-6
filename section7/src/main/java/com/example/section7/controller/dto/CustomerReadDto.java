package com.example.section7.controller.dto;

import com.example.section7.model.Role;

import java.util.Set;


public record CustomerReadDto(Long id,
                              String email,
                              String password,
                              Role role,
                              Set<String> authorities) {

}
