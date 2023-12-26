package org.example.service;

import org.example.model.Trainer;
import org.example.model.TrainingType;
import org.example.model.User;
import org.example.repository.TrainerDAO;
import org.example.util.PasswordGenerator;
import org.example.util.UsernameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest {

    @Mock
    private TrainerDAO trainerDAO;

    @Mock
    private UsernameGenerator usernameGenerator;

    @Mock
    private PasswordGenerator passwordGenerator;

    @InjectMocks
    private TrainerService trainerService;

    private Trainer trainer;
    private User user;
    private TrainingType trainingType;

    @BeforeEach
    public void setUp() {
        user = new User();
        trainingType = new TrainingType();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setPassword("password");
        user.setActive(true);
        trainingType.setName("Fitness");

        trainer = new Trainer();
        trainer.setId(1);
        trainer.setUser(user);
        trainer.setTrainingType(trainingType);
    }

    @Test
    public void createTrainerWhenTrainerPassedThenTrainerCreated() {
        when(usernameGenerator.generateUsername(user)).thenReturn("johndoe");
        when(passwordGenerator.generateRandomPassword()).thenReturn("password");

        trainerService.create(trainer);

        verify(usernameGenerator).generateUsername(user);
        verify(passwordGenerator).generateRandomPassword();
        verify(trainerDAO).create(trainer);
    }

    @Test
    public void updateTrainerWhenTrainerPassedThenTrainerUpdated() {
        when(trainerDAO.get(trainer.getId())).thenReturn(trainer);

        trainerService.updateTrainer(trainer);

        verify(trainerDAO).updateTrainer(trainer);
    }

    @Test
    public void getTrainerWhenTrainerIdPassedThenTrainerReturned() {
        when(trainerDAO.get(1)).thenReturn(trainer);

        Trainer result = trainerService.get(1);

        verify(trainerDAO).get(1);
    }
}
