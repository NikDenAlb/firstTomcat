package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.UserUpdateDTO;
import com.astonhome.firsttomcat.entity.User;

public class UserUpdateMapperImpl implements UserUpdateMapper {
    @Override
    public User toEntity(UserUpdateDTO userUpdateDTO) {
        User user = new User();
        user.setId(userUpdateDTO.getId());
        user.setName(userUpdateDTO.getName());
        return user;
    }
}
