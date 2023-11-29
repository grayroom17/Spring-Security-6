package com.example.section7.mapper;

import com.example.section7.controller.dto.CustomerCreateDto;
import com.example.section7.controller.dto.CustomerReadDto;
import com.example.section7.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Customer fromCreateDto(CustomerCreateDto source);

    CustomerReadDto toReadDto(Customer source);

}
