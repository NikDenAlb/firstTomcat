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
                Arguments.of(COACH1),
                Arguments.of(COACH2),
                Arguments.of(COACH3)
        );
    }

    @BeforeEach
    public void setUp() {
        coachUpdateMapper = new CoachUpdateMapperImpl();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void toEntity(Coach coach) {
        CoachUpdateDTO coachUpdateDTO = new CoachUpdateDTO();
        coachUpdateDTO.setId(coach.getId());
        coachUpdateDTO.setName(coach.getName());
        assertEquals(coach.getId(), coachUpdateMapper.toEntity(coachUpdateDTO).getId());
        assertEquals(coach.getName(), coachUpdateMapper.toEntity(coachUpdateDTO).getName());
    }
}