package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.UserDTO;
import com.astonhome.firsttomcat.dto.UserPrivateDTO;
import com.astonhome.firsttomcat.entity.User;

public class UserMapperImpl implements UserMapper {
    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        return userDTO;
    }

    @Override
    public UserPrivateDTO toPrivateDTO(User user) {
        UserPrivateDTO userPrivateDTO = new UserPrivateDTO();
        userPrivateDTO.setId(user.getId());
        userPrivateDTO.setName(user.getName());
        userPrivateDTO.setHealth(user.getHealth());
        return userPrivateDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        return user;
    }
}