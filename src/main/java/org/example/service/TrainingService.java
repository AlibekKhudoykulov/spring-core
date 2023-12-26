package org.example.service;

import org.example.dto.TrainingDto;
import org.example.model.Trainee;
import org.example.model.Trainer;
import org.example.model.Training;
import org.example.model.TrainingType;
import org.example.repository.TraineeDAO;
import org.example.repository.TrainerDAO;
import org.example.repository.TrainingDAO;
import org.example.repository.TrainingTypeDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService implements BaseService<Training>{
    private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);

    @Autowired
    private TrainingDAO trainingDAO;

    @Autowired
    private TrainerDAO trainerDAO;

    @Autowired
    private TraineeDAO traineeDAO;

    @Autowired
    private TrainingTypeDAO trainingTypeDAO;

    @Override
    public void create(Training entity) {
        trainingDAO.create(entity);
        logger.info("Training created: {}", entity.getId());
    }

    @Override
    public Training get(int id) {
        logger.debug("Getting Training: {}", id);

        Training training = trainingDAO.get(id);

        if (training != null) {
            logger.info("Retrieved Training details for ID {}: {}", id, training.getTrainingName());
        } else {
            logger.error("Training with ID {} not found", id);
        }
        return training;
    }
    public void createTraining(TrainingDto trainingDto) {
        Training training = new Training();
        Trainer trainer = trainerDAO.get(trainingDto.getTrainerId());
        Trainee trainee = traineeDAO.get(trainingDto.getTraineeId());
        TrainingType trainingType = trainingTypeDAO.getTrainingType(trainingDto.getTrainingTypeId());

        training.setId(trainingDto.getId());
        training.setTrainer(trainer);
        training.setTrainee(trainee);
        training.setTrainingType(trainingType);
        training.setTrainingDate(trainingDto.getTrainingDate());
        training.setTrainingName(trainingDto.getTrainingName());
        training.setTrainingDuration(trainingDto.getTrainingDuration());

        logger.debug("Creating Training: {} for Trainee {} by Trainer {}", training.getTrainingName(), trainee.getId(), trainer.getId());
        create(training);
    }

}