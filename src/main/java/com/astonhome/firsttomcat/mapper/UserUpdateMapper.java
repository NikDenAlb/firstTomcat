package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.UserUpdateDTO;
import com.astonhome.firsttomcat.entity.User;
import org.mapstruct.factory.Mappers;


public interface UserUpdateMapper {
    UserUpdateMapper INSTANCE = Mappers.getMapper(UserUpdateMapper.class);

    User toEntity(UserUpdateDTO userUpdateDTO);
}