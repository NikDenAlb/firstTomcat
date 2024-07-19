package com.astonhome.firsttomcat.repository;

import com.astonhome.firsttomcat.entity.Coach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.astonhome.firsttomcat.CONST.*;
import static com.astonhome.firsttomcat.CONST.COACH3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CoachDAOTest {
    private CoachDAO coachDAO;

    public static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(COACHNAME1, COACH1),
                Arguments.of(COACHNAME2, COACH2),
                Arguments.of(COACHNAME3, COACH3)
        );
    }

    @BeforeEach
    public void setUp() {
        coachDAO = new CoachDAO();
    }


    @Test
    @Order(5)
    void getCoach() {
        assertNull(coachDAO.getCoach(2));
        Long id = coachDAO.addCoach(COACH1).getId();
        assertEquals(COACHNAME1, coachDAO.getCoach(id).getName());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    @Order(1)
    void addCoach(String name, Coach coach) {
        coach = coachDAO.addCoach(coach);

        assertNotNull(coach.getId());
        assertEquals(name, coach.getName());
    }

    @Test
    @Order(2)
    void getAllCoaches() {
        List<Coach> coaches = coachDAO.getAllCoaches();

        List<String> names = coaches.stream().map(Coach::getName).toList();

        assertThat(names).contains(COACHNAME1, COACHNAME2, COACHNAME3);
    }

    @Test
    @Order(3)
    void updateCoach() {
        coachDAO.updateCoach(2, COACH3);
        assertEquals(COACHNAME3, coachDAO.getCoach(2).getName());

        coachDAO.updateCoach(3, COACH3);
        assertEquals(COACHNAME3, coachDAO.getCoach(2).getName());
    }

    @Test
    @Order(4)
    void deleteCoach() {
        coachDAO.deleteCoach(2);
        assertNull(coachDAO.getCoach(2));
    }

    @Test
    void getAllCoachByUserId() {
    }
}