package com.example.section9.controller.dto;

public record CustomerReadDto(Long id,
                              String name,
                              String email,
                              String mobileNumber,
                              String role) {

}
