package com.example.section4.mapper;

import com.example.section4.controller.dto.CustomerCreateDto;
import com.example.section4.controller.dto.CustomerReadDto;
import com.example.section4.model.Customer;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapper {

    Customer fromCreateDto(CustomerCreateDto source);

    CustomerReadDto toReadDto(Customer source);

}
