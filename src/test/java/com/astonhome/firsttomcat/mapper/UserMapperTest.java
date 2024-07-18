package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.UserDTO;
import com.astonhome.firsttomcat.dto.UserPrivateDTO;
import com.astonhome.firsttomcat.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.astonhome.firsttomcat.CONST.*;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private UserMapper userMapper;

    public static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(USER1DTO, USER1),
                Arguments.of(USER2DTO, USER2),
                Arguments.of(USER3DTO, USER3)
        );
    }

    public static Stream<Arguments> provideParamsForPrivateTests() {
        return Stream.of(
                Arguments.of(USER1, USERPRIVATEDTO1),
                Arguments.of(USER2, USERPRIVATEDTO2),
                Arguments.of(USER3, USERPRIVATEDTO3)
        );
    }

    @BeforeEach
    public void setUp() {
        userMapper = new UserMapperImpl();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void toDTO(UserDTO outputUserDTO, User inputUser) {
        assertEquals(outputUserDTO.getId(), userMapper.toDTO(inputUser).getId());
        assertEquals(outputUserDTO.getName(), userMapper.toDTO(inputUser).getName());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void toEntity(UserDTO inputUserDTO, User outputUser) {
        assertEquals(outputUser.getId(), userMapper.toEntity(inputUserDTO).getId());
        assertEquals(outputUser.getName(), userMapper.toEntity(inputUserDTO).getName());
    }


    @ParameterizedTest
    @MethodSource("provideParamsForPrivateTests")
    void toPrivateDTO(User inputUser, UserPrivateDTO outputPrivateDTO) {
        assertEquals(outputPrivateDTO.getId(), userMapper.toPrivateDTO(inputUser).getId());
        assertEquals(outputPrivateDTO.getName(), userMapper.toPrivateDTO(inputUser).getName());
        assertEquals(outputPrivateDTO.getHealth(), userMapper.toPrivateDTO(inputUser).getHealth());
    }
}