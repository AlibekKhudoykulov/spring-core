package org.example.dataInitializer;

import org.example.dto.TrainingDto;
import org.example.model.Trainee;
import org.example.model.Trainer;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFacade {

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    @Autowired
    public ServiceFacade(TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    public void createTrainee(Trainee trainee) {
        traineeService.create(trainee);
    }

    public void createTrainer(Trainer trainer) {
        trainerService.create(trainer);
    }

    public void createTraining(TrainingDto trainingDto) {
        trainingService.createTraining(trainingDto);
    }
}