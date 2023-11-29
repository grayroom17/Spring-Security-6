package com.example.section7.controller.dto;

public record CustomerReadDto(Long id,
                              String name,
                              String email,
                              String mobileNumber,
                              String role) {

}
