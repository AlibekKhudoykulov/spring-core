package org.example.service;

import org.example.model.Trainee;
import org.example.repository.TraineeDAO;

import java.util.List;

public class TraineeService {
    private final TraineeDAO traineeDAO = new TraineeDAO();

    public void createTrainee(Trainee trainee) {
        traineeDAO.insertTrainee(trainee);
    }

    public void updateTrainee(Trainee trainee) {
        traineeDAO.updateTrainee(trainee);
    }

    public void deleteTrainee(int id) {
        traineeDAO.deleteTrainee(id);
    }

    public Trainee getTraineeById(int id) {
        return traineeDAO.selectTraineeById(id);
    }

    public List<Trainee> getAllTrainees() {
        return traineeDAO.selectAllTrainees();
    }
}