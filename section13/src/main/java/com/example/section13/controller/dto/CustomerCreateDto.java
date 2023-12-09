package com.example.section13.controller.dto;

import java.util.Set;


public record CustomerCreateDto(String name,
                                String email,
                                String mobileNumber,
                                String password,
                                String role,
                                Set<String> authorities) {

}
