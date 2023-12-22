package org.example.repository;

import org.example.model.Trainee;
import org.example.model.User;
import org.example.storage.InMemoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class TraineeDAOTest {
    @InjectMocks
    TraineeDAO traineeDAO;

    @Mock
    InMemoryStorage storage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(1);
        User user = new User();
        user.setId(2);
        trainee.setUser(user);

        traineeDAO.createTrainee(trainee);

        verify(storage).addToStorage("users", user.getId(), user);
        verify(storage).addToStorage("trainees", trainee.getId(), trainee);
    }

    @Test
    void testUpdateTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(1);

        traineeDAO.updateTrainee(trainee);

        verify(storage).updateStorage("trainees", trainee.getId(), trainee);
    }

    @Test
    void testDeleteTrainee() {
        traineeDAO.deleteTrainee(1);

        verify(storage).removeFromStorage("trainees", 1);
    }

    @Test
    void testGetTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(1);

        when(storage.getFromStorage("trainees", 1)).thenReturn(trainee);

        Trainee result = traineeDAO.getTrainee(1);

        assertEquals(trainee, result);
    }
}