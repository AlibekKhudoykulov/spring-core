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
public class TrainingService{
    private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);

    private TrainingDAO trainingDAO;
    private TrainerDAO trainerDAO;
    private TraineeDAO traineeDAO;
    private TrainingTypeDAO trainingTypeDAO;

    @Autowired
    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Autowired
    public void setTrainerDAO(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    @Autowired
    public void setTraineeDAO(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    @Autowired
    public void setTrainingTypeDAO(TrainingTypeDAO trainingTypeDAO) {
        this.trainingTypeDAO = trainingTypeDAO;
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

        trainingDAO.create(training);
        logger.info("Training created: {}", training.getId());
    }

    public Training getTraining(int trainingId) {
        logger.debug("Getting Training: {}", trainingId);

        Training training = trainingDAO.get(trainingId);

        if (training != null) {
            logger.info("Retrieved Training details for ID {}: {}", trainingId, training.getTrainingName());
        } else {
            logger.error("Training with ID {} not found", trainingId);
        }
        return training;
    }

}