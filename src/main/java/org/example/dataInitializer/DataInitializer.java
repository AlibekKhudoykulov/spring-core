package org.example.dataInitializer;

import org.example.constant.EntityType;
import org.example.dto.TrainingDto;
import org.example.model.Trainee;
import org.example.model.Trainer;
import org.example.model.TrainingType;
import org.example.model.User;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

@Component
public class DataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final ServiceFacade serviceFacade;

    public DataInitializer(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @PostConstruct
    public void initializeSampleData() {
        createSampleTrainees();
    }

    private void createSampleTrainees() {
        try {
            File file = new File("data");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                EntityType type = EntityType.valueOf(parts[0].toUpperCase());

                switch (type) {
                    case TRAINEE:
                        Trainee trainee = createTrainee(Integer.parseInt(parts[1]), parts[2], parts[3], parts[4]);
                        serviceFacade.createTrainee(trainee);
                        break;

                    case TRAINER:
                        Trainer trainer = createTrainer(Integer.parseInt(parts[1]), parts[2], parts[3], parts[4]);
                        serviceFacade.createTrainer(trainer);
                        break;

                    case TRAINING:
                        TrainingDto training = createTraining(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                                Integer.parseInt(parts[3]), parts[4], Integer.parseInt(parts[5]), Float.parseFloat(parts[6]));
                        serviceFacade.createTraining(training);
                        break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            logger.error("Failed to create sample trainees. File not found: {}", e.getMessage(), e);
        }
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
                                       int trainingTypeId, float trainingDuration) {
        return new TrainingDto(
                id, traineeId, trainerId, trainingName, trainingTypeId, new Date(), trainingDuration);
    }
}

