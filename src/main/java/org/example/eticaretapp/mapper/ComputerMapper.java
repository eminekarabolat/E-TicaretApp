package org.example.eticaretapp.mapper;

import org.example.eticaretapp.entity.products.Computer;
import org.example.eticaretapp.view.Product.VwComputer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComputerMapper {

    ComputerMapper INSTANCE = Mappers.getMapper(ComputerMapper.class);

    VwComputer fromViewComputer(Computer computer);
}
