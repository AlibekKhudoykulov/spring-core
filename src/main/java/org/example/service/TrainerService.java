package org.example.service;

import org.example.model.Trainer;
import org.example.repository.TrainerDAO;

import java.util.List;

public class TrainerService {
    private final TrainerDAO trainerDAO = new TrainerDAO();

    public void createTrainer(Trainer trainer) {
        trainerDAO.insertTrainer(trainer);
    }

    public void updateTrainer(Trainer trainer) {
        trainerDAO.updateTrainer(trainer);
    }

    public Trainer getTrainerById(int id) {
        return trainerDAO.selectTrainerById(id);
    }

    public List<Trainer> getAllTrainers() {
        return trainerDAO.selectAllTrainers();
    }
}