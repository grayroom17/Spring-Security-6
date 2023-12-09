package com.example.section13.controller.dto;

public record CustomerReadDto(Long id,
                              String name,
                              String email,
                              String mobileNumber,
                              String role) {

}
