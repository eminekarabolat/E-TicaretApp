package org.example.eticaretapp.mapper;

import org.example.eticaretapp.dto.request.RegisterRequestDto;
import org.example.eticaretapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User fromRegisterRequestDto(RegisterRequestDto registerRequestDto);
}
