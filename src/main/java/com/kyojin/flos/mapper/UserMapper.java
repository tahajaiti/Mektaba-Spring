package com.kyojin.flos.mapper;


import com.kyojin.flos.dto.UserDTO;
import com.kyojin.flos.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role.name", target = "roleName")
    UserDTO toDto(User user);
}
