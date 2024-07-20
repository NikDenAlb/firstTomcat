package com.astonhome.firsttomcat.service;

import com.astonhome.firsttomcat.dto.TrainingDTO;
import com.astonhome.firsttomcat.repository.TrainingDAO;

public class TrainingService {
    private final TrainingDAO trainingDAO;

    public TrainingService(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    public boolean checkTraining(TrainingDTO trainingDTO) {
        return trainingDAO.checkTraining(trainingDTO);
    }

    public boolean setTraining(TrainingDTO trainingDTO) {
        return trainingDAO.setTraining(trainingDTO);
    }

    public boolean deleteTraining(TrainingDTO trainingDTO) {
        return trainingDAO.deleteTraining(trainingDTO);
    }
}