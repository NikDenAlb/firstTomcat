package com.astonhome.firsttomcat.service;

import com.astonhome.firsttomcat.dto.CoachDTO;
import com.astonhome.firsttomcat.dto.CoachUpdateDTO;
import com.astonhome.firsttomcat.entity.Coach;
import com.astonhome.firsttomcat.repository.CoachDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.ArrayList;
import java.util.List;

import static com.astonhome.firsttomcat.CONST.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoachServiceTest {
    @InjectMocks
    private CoachService coachService;
    @Mock
    private CoachDAO mockCoachDAO;


    @Test
    void getCoachById() {
        Coach coach = new Coach(1L, "name", null);
        when(mockCoachDAO.getCoach(1L)).thenReturn(coach);
        CoachDTO out = coachService.getCoachById(1L);
        assertEquals("name", out.getName());
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
        Coach coach = new Coach(1L, "name", null);
        when(mockCoachDAO.addCoach(any())).thenReturn(coach);

        CoachDTO out = coachService.saveCoach(COACH1DTO);
        assertEquals(coach.getName(), out.getName());
        assertEquals(coach.getId(), out.getId());
    }

    @Test
    void updateCoach() {
        Coach coach = new Coach(10L, "name", null);
        CoachUpdateDTO coachUpdateDTO = new CoachUpdateDTO(10L, coach.getName());
        when(mockCoachDAO.updateCoach(anyLong(), any())).thenReturn(coach);

        Coach out = coachService.updateCoach(coachUpdateDTO);
        if (out == null) {
            System.out.println("Coach not found");
        }

        assertEquals(coach.getName(), out.getName());
        assertEquals(coach.getId(), out.getId());
    }
}