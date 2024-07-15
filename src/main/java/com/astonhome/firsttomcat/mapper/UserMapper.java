package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.UserDTO;
import com.astonhome.firsttomcat.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toEntity (UserDTO userDTO);
}