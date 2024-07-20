package com.astonhome.firsttomcat.service;

import com.astonhome.firsttomcat.dto.UserDTO;
import com.astonhome.firsttomcat.dto.UserPrivateDTO;
import com.astonhome.firsttomcat.entity.User;
import com.astonhome.firsttomcat.mapper.UserMapper;
import com.astonhome.firsttomcat.repository.UserDAO;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDTO getUserById(Long id) {
        User user = userDAO.getUser(id);
        if (user == null) {
            return null;
        }
        return UserMapper.INSTANCE.toDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        return users.stream().map(UserMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        return UserMapper.INSTANCE.toDTO(userDAO.addUser(user));
    }

    public void updateUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        userDAO.updateUser(user.getId(), user);
    }

    public UserDTO deleteUser(Long id) {
        return UserMapper.INSTANCE.toDTO(userDAO.deleteUser(id));
    }

    public List<UserDTO> getAllUserByCoachId(long id) {
        return userDAO.getAllUserByCoachId(id).stream().map(UserMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    public UserPrivateDTO getUserPrivateById(Long id) {
        User user = userDAO.getUser(id);
        if (user == null) {
            return null;
        }
        return UserMapper.INSTANCE.toPrivateDTO(user);
    }
}
