package com.example.section13.mapper;

import com.example.section13.model.Customer;
import com.example.section13.controller.dto.CustomerCreateDto;
import com.example.section13.controller.dto.CustomerReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Customer fromCreateDto(CustomerCreateDto source);

    CustomerReadDto toReadDto(Customer source);

}
