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
public class TraineeService implements BaseService<Trainee>{

    private static final Logger logger = LoggerFactory.getLogger(TraineeService.class);

    private TraineeDAO traineeDAO;

    @Autowired
    private UsernameGenerator usernameGenerator;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    public void setTraineeDAO(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    @Override
    public void create(Trainee entity) {
        User user = entity.getUser();
        String username = usernameGenerator.generateUsername(user);
        user.setUsername(username);

        String password = passwordGenerator.generateRandomPassword();
        user.setPassword(password);

        entity.setUser(user);

        logger.debug("Creating Trainee: {} {}", entity.getUser().getFirstName(), entity.getUser().getLastName());

        traineeDAO.create(entity);

        logger.info("Trainee created: {}", entity.getId());
    }

    @Override
    public Trainee get(int id) {
        logger.debug("Getting Trainee: {}", id);

        Trainee trainee =  traineeDAO.get(id);

        if (trainee != null) {
            logger.info("Retrieved Trainee details for ID {}: {} {}", id, trainee.getUser().getFirstName(), trainee.getUser().getLastName());
        } else {
            logger.error("Trainee with ID {} not found", id);
        }
        return trainee;
    }

    public void updateTrainee(Trainee trainee) {
        Trainee existingTrainee = traineeDAO.get(trainee.getId());
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
}