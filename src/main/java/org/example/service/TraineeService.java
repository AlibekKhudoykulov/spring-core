package org.example.service;


import org.example.model.Trainee;
import org.example.model.User;
import org.example.repository.TraineeDAO;
import org.example.util.PasswordGenerator;
import org.example.util.UsernameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TraineeService {

    private static final Logger logger = LoggerFactory.getLogger(TraineeService.class);

    @Autowired
    private TraineeDAO traineeDAO;

    @Autowired
    private UsernameGenerator usernameGenerator;

    @Autowired
    private PasswordGenerator passwordGenerator;

    public void createTrainee(Trainee trainee) {
        // Generate username
        User user = trainee.getUser();
        String username = usernameGenerator.generateUsername(user);
        user.setUsername(username);

        // Generate password
        String password = passwordGenerator.generateRandomPassword();
        user.setPassword(password);

        trainee.setUser(user);

        logger.debug("Creating Trainee: {} {}", trainee.getUser().getFirstName(), trainee.getUser().getLastName());

        // Persist trainee
        traineeDAO.createTrainee(trainee);

        logger.info("Trainee created: {}", trainee.getId());

    }

    public void updateTrainee(Trainee trainee) {
        Trainee existingTrainee = traineeDAO.getTrainee(trainee.getId());
        if(existingTrainee!=null) {
            User user = existingTrainee.getUser();
            user.setFirstName(trainee.getUser().getFirstName());
            user.setLastName(trainee.getUser().getLastName());

            existingTrainee.setAddress(trainee.getAddress());
            existingTrainee.setDateOfBirth(trainee.getDateOfBirth());
            existingTrainee.setUser(user);

            logger.debug("Updating Trainee: {}", trainee.getId());

            traineeDAO.updateTrainee(existingTrainee);

            logger.info("Updating Trainee: {}", trainee.getId());
        }else {
            logger.error("Trainee with ID {} not found for update", trainee.getId());
        }
    }

    public void deleteTrainee(int traineeId) {
        logger.debug("Deleting Trainee: {}", traineeId);

        traineeDAO.deleteTrainee(traineeId);

        logger.info("Trainee deleted: {}", traineeId);
    }

    public Trainee getTrainee(int traineeId) {
        logger.debug("Getting Trainee: {}", traineeId);

        Trainee trainee = (Trainee) traineeDAO.getTrainee(traineeId);

        if (trainee != null) {
            logger.info("Retrieved Trainee details for ID {}: {} {}", traineeId, trainee.getUser().getFirstName(), trainee.getUser().getLastName());
        } else {
            logger.error("Trainee with ID {} not found", traineeId);
        }
        return trainee;    }

}