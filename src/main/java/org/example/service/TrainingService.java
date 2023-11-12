package org.example.service;

import org.example.model.Training;
import org.example.repository.TrainingDAO;

import java.util.List;

public class TrainingService {
    private final TrainingDAO trainingDAO = new TrainingDAO();

    public void createTraining(Training training) {
        trainingDAO.insertTraining(training);
    }

    public Training getTrainingById(int id) {
        return trainingDAO.selectTrainingById(id);
    }

    public List<Training> getAllTrainings() {
        return trainingDAO.selectAllTrainings();
    }
}