package com.astonhome.firsttomcat.service;

import com.astonhome.firsttomcat.dto.CoachDTO;
import com.astonhome.firsttomcat.entity.Coach;
import com.astonhome.firsttomcat.repository.CoachDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.astonhome.firsttomcat.CONST.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CoachServiceTest {

    @Mock
    private CoachDAO mockCoachDAO;


    @InjectMocks
    private CoachService coachService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        coachService = new CoachService();
    }

    @Test
    void getCoachById() {
        when(mockCoachDAO.getCoach(COACH1.getId())).thenReturn(COACH2);

        CoachDTO out = coachService.getCoachById(COACH2.getId());

        assertEquals(COACHNAME3, out.getName());
    }

    @Test
    void getAllCoaches() {
        when(mockCoachDAO.getAllCoaches()).thenReturn(new ArrayList<>(List.of(COACH1, COACH2, COACH3)));

        List<CoachDTO> out = coachService.getAllCoaches();

        List<String> names = out.stream().map(CoachDTO::getName).toList();

        assertThat(names).contains(COACHNAME1, COACHNAME2, COACHNAME3);
    }

    @Test
    void saveCoach() {
        when(mockCoachDAO.addCoach(COACH1)).thenReturn(COACH1);

        CoachDTO out = coachService.saveCoach(COACH1DTO);
        assertEquals(COACH1.getName(), out.getName());

    }

    @Test
    void updateCoach() {
        when(mockCoachDAO.updateCoach(COACH1.getId(), COACH1)).thenReturn(COACH1);

        Coach out = coachService.updateCoach(UCOACHUPDATEDTO1);
        assertEquals(COACH1.getName(), out.getName());
    }
}