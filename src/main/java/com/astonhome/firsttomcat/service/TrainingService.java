package com.astonhome.firsttomcat.service;

import com.astonhome.firsttomcat.dto.TrainingDTO;
import com.astonhome.firsttomcat.repository.TrainingDAO;

public class TrainingService {
    public boolean checkTraining(TrainingDTO trainingDTO) {
        return TrainingDAO.checkTraining(trainingDTO);
    }
    public void setTraining(TrainingDTO trainingDTO) {
       TrainingDAO.setTraining(trainingDTO);
    }
    public void deleteTraining(TrainingDTO trainingDTO) {
        TrainingDAO.deleteTraining(trainingDTO);
    }
}
