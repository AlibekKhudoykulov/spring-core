package org.example.dataInitializer;

import org.example.dto.TrainingDto;
import org.example.model.Trainee;
import org.example.model.Trainer;
import org.example.model.TrainingType;
import org.example.model.User;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;

import java.util.Date;
public class SampleDataInitializer {

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    public SampleDataInitializer(TraineeService traineeService,
                                 TrainerService trainerService,
                                 TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    public void initializeSampleData() {
        createSampleTrainees();
        // Add other sample data initialization as needed
    }

    private void createSampleTrainees() {
        Trainee trainee1 = createTrainee(1, "John", "Doe", "123 Main St");
        Trainee trainee2 = createTrainee(2, "Jane", "Smith", "456 Oak St");

        traineeService.createTrainee(trainee1);
        traineeService.createTrainee(trainee2);

        Trainer trainer1 = createTrainer(3, "John", "Doe", "Fitness");
        Trainer trainer2 = createTrainer(4, "Jane", "Smith", "Yoga");

        trainerService.createTrainer(trainer1);
        trainerService.createTrainer(trainer2);

        TrainingDto training1 = createTraining(1,1,3,"A",3,15);
        TrainingDto training2 = createTraining(2,2,4,"B",4,30);

        trainingService.createTraining(training1);
        trainingService.createTraining(training2);
    }

    private Trainee createTrainee(int id, String firstName, String lastName, String address) {
        Trainee trainee = new Trainee();
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        trainee.setUser(user);
        trainee.setId(id);
        trainee.setDateOfBirth(new Date());
        trainee.setAddress(address);
        return trainee;
    }

    private Trainer createTrainer(int id, String firstName, String lastName, String trainingType) {
        Trainer trainer = new Trainer();
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        TrainingType trainingType1 = new TrainingType();
        trainingType1.setId(id);
        trainingType1.setName(trainingType);

        trainer.setTrainingType(trainingType1);
        trainer.setUser(user);
        trainer.setId(id);
        return trainer;
    }

    private TrainingDto createTraining(int id, int traineeId, int trainerId, String trainingName,
                                    int trainingTypeId, float trainingDuration){
        return new TrainingDto(
                id,traineeId,trainerId,trainingName,trainingTypeId,new Date(),trainingDuration);
    }
}

