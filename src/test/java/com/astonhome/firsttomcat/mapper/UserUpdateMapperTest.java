package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.UserUpdateDTO;
import com.astonhome.firsttomcat.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.astonhome.firsttomcat.CONST.*;
import static org.junit.jupiter.api.Assertions.*;

class UserUpdateMapperTest {
    private UserUpdateMapper userUpdateMapper;

    public static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(USERUPDATEDTO1, USER1),
                Arguments.of(USERUPDATEDTO2, USER2),
                Arguments.of(USERUPDATEDTO3, USER3)
        );
    }

    @BeforeEach
    public void setUp() {
        userUpdateMapper = new UserUpdateMapperImpl();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void toEntity(UserUpdateDTO userUpdateDTO, User user) {
        assertEquals(user.getId(), userUpdateMapper.toEntity(userUpdateDTO).getId());
        assertEquals(user.getName(), userUpdateMapper.toEntity(userUpdateDTO).getName());
    }
}