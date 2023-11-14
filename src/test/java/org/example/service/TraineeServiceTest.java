package org.example.service;

import org.example.model.Trainee;
import org.example.model.User;
import org.example.repository.TraineeDAO;
import org.example.util.PasswordGenerator;
import org.example.util.UsernameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceTest {

    @Mock
    private TraineeDAO traineeDAO;

    @Mock
    private UsernameGenerator usernameGenerator;

    @Mock
    private PasswordGenerator passwordGenerator;

    @InjectMocks
    private TraineeService traineeService;

    private Trainee trainee;

    @BeforeEach
    public void setUp() {
        trainee = new Trainee();
        trainee.setId(1);
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        trainee.setUser(user);
    }

    @Test
    @DisplayName("Test createTrainee when Trainee then createTrainee called")
    public void testCreateTraineeWhenTraineeThenCreateTraineeCalled() {
        when(usernameGenerator.generateUsername(any(User.class))).thenReturn("johndoe");
        when(passwordGenerator.generateRandomPassword()).thenReturn("password123");

        traineeService.createTrainee(trainee);

        verify(usernameGenerator, times(1)).generateUsername(any(User.class));
        verify(passwordGenerator, times(1)).generateRandomPassword();
        verify(traineeDAO, times(1)).createTrainee(trainee);
    }

    @Test
    @DisplayName("Test updateTrainee when Trainee then updateTrainee called")
    public void testUpdateTraineeWhenTraineeThenUpdateTraineeCalled() {
        traineeService.updateTrainee(trainee);
        verify(traineeDAO, times(1)).updateTrainee(trainee);
    }

    @Test
    @DisplayName("Test deleteTrainee when TraineeId then deleteTrainee called")
    public void testDeleteTraineeWhenTraineeIdThenDeleteTraineeCalled() {
        traineeService.deleteTrainee(trainee.getId());
        verify(traineeDAO, times(1)).deleteTrainee(trainee.getId());
    }

    @Test
    @DisplayName("Test getTrainee when TraineeId then Trainee returned")
    public void testGetTraineeWhenTraineeIdThenTraineeReturned() {
        when(traineeDAO.getTrainee(trainee.getId())).thenReturn(trainee);
        Trainee returnedTrainee = traineeService.getTrainee(trainee.getId());
        assertSame(trainee, returnedTrainee);
    }
}