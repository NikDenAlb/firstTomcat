package com.astonhome.firsttomcat.service;

import com.astonhome.firsttomcat.dto.TrainingDTO;
import com.astonhome.firsttomcat.repository.TrainingDAO;

public class TrainingService {
    private final TrainingDAO trainingDAO = new TrainingDAO();

    public boolean checkTraining(TrainingDTO trainingDTO) {
        return trainingDAO.checkTraining(trainingDTO);
    }

    public void setTraining(TrainingDTO trainingDTO) {
        trainingDAO.setTraining(trainingDTO);
    }

    public void deleteTraining(TrainingDTO trainingDTO) {
        trainingDAO.deleteTraining(trainingDTO);
    }
}