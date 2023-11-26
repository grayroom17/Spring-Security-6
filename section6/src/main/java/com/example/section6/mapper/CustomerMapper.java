package com.example.section6.mapper;

import com.example.section6.controller.dto.CustomerCreateDto;
import com.example.section6.controller.dto.CustomerReadDto;
import com.example.section6.model.Customer;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapper {

    Customer fromCreateDto(CustomerCreateDto source);

    CustomerReadDto toReadDto(Customer source);

}
