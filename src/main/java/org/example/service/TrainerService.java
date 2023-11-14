package org.example.service;

import org.example.model.Trainer;
import org.example.model.TrainingType;
import org.example.model.User;
import org.example.repository.TrainerDAO;
import org.example.util.PasswordGenerator;
import org.example.util.UsernameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    private static final Logger logger = LoggerFactory.getLogger(TraineeService.class);


    @Autowired
    private TrainerDAO trainerDAO;

    @Autowired
    private UsernameGenerator usernameGenerator;

    @Autowired
    private PasswordGenerator passwordGenerator;

    public void createTrainer(Trainer trainer) {
        User user = trainer.getUser();
        String username = usernameGenerator.generateUsername(user);
        user.setUsername(username);

        // Generate password
        String password = passwordGenerator.generateRandomPassword();
        user.setPassword(password);

        trainer.setUser(user);

        logger.info("Creating Trainer: {} {}", trainer.getUser().getFirstName(), trainer.getUser().getLastName());

        trainerDAO.createTrainer(trainer);

        logger.info("Trainer created: {}", trainer.getId());

    }

    public void updateTrainer(Trainer trainer) {
        Trainer existingTrainer = (Trainer) trainerDAO.getTrainer(trainer.getId());

        if (existingTrainer != null) {
            User user = existingTrainer.getUser();
            TrainingType trainingType = existingTrainer.getTrainingType();
            user.setFirstName(trainer.getUser().getFirstName());
            user.setLastName(trainer.getUser().getLastName());
            trainingType.setName(trainer.getTrainingType().getName());

            existingTrainer.setTrainingType(trainingType);
            existingTrainer.setUser(user);

            logger.info("Updating Trainer: {}", trainer.getId());

            trainerDAO.updateTrainer(existingTrainer);

            logger.info("Trainer updated: {}", trainer.getId());

        }else {
            logger.error("Trainer with ID {} not found for update", trainer.getId());
        }
    }

    public Trainer getTrainer(int trainerId) {
        logger.info("Getting Trainer: {}", trainerId);

        Trainer trainer = (Trainer) trainerDAO.getTrainer(trainerId);
        if (trainer != null) {
            logger.info("Retrieved Trainer details for ID {}: {} {}", trainerId, trainer.getUser().getFirstName(), trainer.getUser().getLastName());
        } else {
            logger.error("Trainer with ID {} not found", trainerId);
        }
        return trainer;
    }

}