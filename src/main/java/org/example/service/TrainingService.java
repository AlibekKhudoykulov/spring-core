package org.example.service;

import org.example.model.Training;
import org.example.model.TrainingType;
import org.example.repository.TrainingDAO;
import org.example.repository.TrainingTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    @Autowired
    private TrainingDAO trainingDAO;

    @Autowired
    private TrainingTypeDAO trainingTypeDAO;

    public void createTraining(Training training) {
        trainingDAO.createTraining(training);
    }

    public Training getTraining(int trainingId) {
        return trainingDAO.getTraining(trainingId);
    }

    public void createTrainingType(TrainingType trainingType) {
        trainingTypeDAO.createTrainingType(trainingType);
    }

    public TrainingType getTrainingType(int trainingTypeId) {
        return trainingTypeDAO.getTrainingType(trainingTypeId);
    }
}