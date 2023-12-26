package org.example.repository;

import org.example.contant.EntityType;
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
    void createTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(1);
        User user = new User();
        user.setId(2);
        trainee.setUser(user);

        traineeDAO.createTrainee(trainee);

        verify(storage).addToStorage(EntityType.USER, user.getId(), user);
        verify(storage).addToStorage(EntityType.TRAINEE, trainee.getId(), trainee);
    }

    @Test
    void updateTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(1);

        traineeDAO.updateTrainee(trainee);

        verify(storage).updateStorage(EntityType.TRAINEE, trainee.getId(), trainee);
    }

    @Test
    void deleteTrainee() {
        traineeDAO.deleteTrainee(1);

        verify(storage).removeFromStorage(EntityType.TRAINEE, 1);
    }

    @Test
    void getTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(1);

        when(storage.getFromStorage(EntityType.TRAINEE, 1)).thenReturn(trainee);

        Trainee result = traineeDAO.getTrainee(1);

        assertEquals(trainee, result);
    }
}