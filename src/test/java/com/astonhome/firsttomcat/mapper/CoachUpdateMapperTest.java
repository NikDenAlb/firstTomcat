package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.CoachUpdateDTO;
import com.astonhome.firsttomcat.entity.Coach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.astonhome.firsttomcat.CONST.*;
import static com.astonhome.firsttomcat.CONST.COACH3;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoachUpdateMapperTest {
    private CoachUpdateMapper coachUpdateMapper;

    public static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(COACHUPDATEDTO1, COACH1),
                Arguments.of(COACHUPDATEDTO2, COACH2),
                Arguments.of(COACHUPDATEDTO3, COACH3)
        );
    }

    @BeforeEach
    public void setUp() {
        coachUpdateMapper = new CoachUpdateMapperImpl();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void toEntity(CoachUpdateDTO coachUpdateDTO, Coach Coach) {
        assertEquals(coachUpdateMapper.toEntity(coachUpdateDTO).getId(), Coach.getId());
        assertEquals(coachUpdateMapper.toEntity(coachUpdateDTO).getName(), Coach.getName());
    }
}