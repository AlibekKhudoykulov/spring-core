package org.example.service;

import org.example.model.Trainer;
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

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setPassword("password");
        user.setActive(true);

        trainer = new Trainer();
        trainer.setId(1);
        trainer.setUser(user);
    }

    @Test
    public void testCreateTrainerWhenTrainerPassedThenTrainerCreated() {
        // Arrange
        when(usernameGenerator.generateUsername(user)).thenReturn("johndoe");
        when(passwordGenerator.generateRandomPassword()).thenReturn("password");

        // Act
        trainerService.createTrainer(trainer);

        // Assert
        verify(usernameGenerator).generateUsername(user);
        verify(passwordGenerator).generateRandomPassword();
        verify(trainerDAO).createTrainer(trainer);
    }

    @Test
    public void testUpdateTrainerWhenTrainerPassedThenTrainerUpdated() {
        // Act
        trainerService.updateTrainer(trainer);

        // Assert
        verify(trainerDAO).updateTrainer(trainer);
    }

    @Test
    public void testGetTrainerWhenTrainerIdPassedThenTrainerReturned() {
        // Arrange
        when(trainerDAO.getTrainer(1)).thenReturn(trainer);

        // Act
        Trainer result = trainerService.getTrainer(1);

        // Assert
        verify(trainerDAO).getTrainer(1);
    }
}
