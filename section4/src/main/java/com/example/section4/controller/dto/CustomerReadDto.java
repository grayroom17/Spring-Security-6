package com.example.section4.controller.dto;

import com.example.section4.model.Role;


public record CustomerReadDto(Long id,
                              String email,
                              String password,
                              Role role) {

}
