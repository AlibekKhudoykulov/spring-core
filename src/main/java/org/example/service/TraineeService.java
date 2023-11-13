package org.example.service;

import org.example.model.Trainee;
import org.example.repository.TraineeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeService {

    @Autowired
    private TraineeDAO traineeDAO;

    public void createTrainee(Trainee trainee) {
        traineeDAO.createTrainee(trainee);
    }

    public void updateTrainee(Trainee trainee) {
        traineeDAO.updateTrainee(trainee);
    }

    public void deleteTrainee(int traineeId) {
        traineeDAO.deleteTrainee(traineeId);
    }

    public Trainee getTrainee(int traineeId) {
        return traineeDAO.getTrainee(traineeId);
    }

}