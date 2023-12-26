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
public class TrainerService implements BaseService<Trainer> {
    private static final Logger logger = LoggerFactory.getLogger(TraineeService.class);

    @Autowired
    private TrainerDAO trainerDAO;

    @Autowired
    private UsernameGenerator usernameGenerator;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Override
    public void create(Trainer entity) {
        User user = entity.getUser();
        String username = usernameGenerator.generateUsername(user);
        user.setUsername(username);

        String password = passwordGenerator.generateRandomPassword();
        user.setPassword(password);

        entity.setUser(user);

        logger.debug("Creating Trainer: {} {}", entity.getUser().getFirstName(), entity.getUser().getLastName());

        trainerDAO.create(entity);

        logger.info("Trainer created: {}", entity.getId());
    }

    @Override
    public Trainer get(int id) {
        logger.debug("Getting Trainer: {}", id);

        Trainer trainer = trainerDAO.get(id);
        if (trainer != null) {
            logger.info("Retrieved Trainer details for ID {}: {} {}", id, trainer.getUser().getFirstName(), trainer.getUser().getLastName());
        } else {
            logger.error("Trainer with ID {} not found", id);
        }
        return trainer;
    }

    public void updateTrainer(Trainer trainer) {
        Trainer existingTrainer = trainerDAO.get(trainer.getId());

        if (existingTrainer != null) {
            User user = existingTrainer.getUser();
            TrainingType trainingType = existingTrainer.getTrainingType();
            user.setFirstName(trainer.getUser().getFirstName());
            user.setLastName(trainer.getUser().getLastName());
            trainingType.setName(trainer.getTrainingType().getName());

            existingTrainer.setTrainingType(trainingType);
            existingTrainer.setUser(user);

            logger.debug("Updating Trainer: {}", trainer.getId());

            trainerDAO.updateTrainer(existingTrainer);

            logger.info("Trainer updated: {}", trainer.getId());

        } else {
            logger.error("Trainer with ID {} not found for update", trainer.getId());
        }
    }
}