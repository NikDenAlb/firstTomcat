package com.astonhome.firsttomcat.service;

import com.astonhome.firsttomcat.dto.UserDTO;
import com.astonhome.firsttomcat.entity.User;
import com.astonhome.firsttomcat.mapper.UserMapper;
import com.astonhome.firsttomcat.repository.UserDAO;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    public UserDTO getUserById(Long id) {
        User user = UserDAO.getUser(id);
        return UserMapper.INSTANCE.toDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = UserDAO.getAllUsers();
        return users.stream().map(UserMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    public void saveUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        UserDAO.addUser(user);
    }

    public void updateUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        UserDAO.updateUser(user.getId(), user);
    }

    public UserDTO deleteUser(Long id) {
        return UserMapper.INSTANCE.toDTO(UserDAO.deleteUser(id));
    }
}