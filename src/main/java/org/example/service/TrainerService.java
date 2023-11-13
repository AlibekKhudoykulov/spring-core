package org.example.service;

import org.example.model.Trainer;
import org.example.repository.TrainerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {

    @Autowired
    private TrainerDAO trainerDAO;

    public void createTrainer(Trainer trainer) {
        trainerDAO.createTrainer(trainer);
    }

    public void updateTrainer(Trainer trainer) {
        trainerDAO.updateTrainer(trainer);
    }

    public Trainer getTrainer(int trainerId) {
        return trainerDAO.getTrainer(trainerId);
    }

}