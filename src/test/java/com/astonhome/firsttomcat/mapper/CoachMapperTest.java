package com.astonhome.firsttomcat.mapper;

import com.astonhome.firsttomcat.dto.CoachDTO;
import com.astonhome.firsttomcat.entity.Coach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.astonhome.firsttomcat.CONST.*;
import static org.junit.jupiter.api.Assertions.*;

class CoachMapperTest {
    private CoachMapper coachMapper;

    public static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(COACH1DTO, COACH1),
                Arguments.of(COACH2DTO, COACH2),
                Arguments.of(COACH3DTO, COACH3)
        );
    }

    @BeforeEach
    public void setUp() {
        coachMapper = new CoachMapperImpl();
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void toDTO(CoachDTO outputCoachDTO, Coach inputCoach) {
        outputCoachDTO.setId(inputCoach.getId());
        assertEquals(outputCoachDTO.getId(), coachMapper.toDTO(inputCoach).getId());
        assertEquals(outputCoachDTO.getName(), coachMapper.toDTO(inputCoach).getName());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    void toEntity(CoachDTO inputCoachDTO, Coach outputCoach) {
        assertEquals(outputCoach.getId(), coachMapper.toEntity(inputCoachDTO).getId());
        assertEquals(outputCoach.getName(), coachMapper.toEntity(inputCoachDTO).getName());

    }
}