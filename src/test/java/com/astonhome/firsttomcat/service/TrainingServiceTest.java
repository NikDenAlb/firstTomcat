package com.astonhome.firsttomcat.service;

import com.astonhome.firsttomcat.dto.TrainingDTO;
import com.astonhome.firsttomcat.repository.TrainingDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TrainingServiceTest {
    private TrainingService trainingService;
    private TrainingDTO trainingDTO1;
    private TrainingDTO trainingDTO2;

    @Mock
    private TrainingDAO mockTrainingDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trainingService = new TrainingService(mockTrainingDAO);
        trainingDTO1 = new TrainingDTO(1L, 1L);
        trainingDTO2 = new TrainingDTO(2L, 2L);
    }

    @Test
    void checkTraining() {
        when(mockTrainingDAO.checkTraining(trainingDTO1)).thenReturn(true);
        when(mockTrainingDAO.checkTraining(trainingDTO2)).thenReturn(false);
        assertTrue(trainingService.checkTraining(trainingDTO1));
        assertFalse(trainingService.checkTraining(trainingDTO2));
    }


    @Test
    void setTraining() {
        when(mockTrainingDAO.setTraining(trainingDTO1)).thenReturn(true);
        when(mockTrainingDAO.setTraining(trainingDTO2)).thenReturn(false);
        assertTrue(trainingService.setTraining(trainingDTO1));
        assertFalse(trainingService.setTraining(trainingDTO2));
    }

    @Test
    void deleteTraining() {
        when(mockTrainingDAO.deleteTraining(trainingDTO1)).thenReturn(true);
        when(mockTrainingDAO.deleteTraining(trainingDTO2)).thenReturn(false);
        assertTrue(trainingService.deleteTraining(trainingDTO1));
        assertFalse(trainingService.deleteTraining(trainingDTO2));
    }
}