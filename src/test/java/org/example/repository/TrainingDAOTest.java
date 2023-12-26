package org.example.repository;

import org.example.constant.EntityType;
import org.example.model.Training;
import org.example.storage.InMemoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrainingDAOTest {

    @InjectMocks
    private TrainingDAO trainingDAO;

    @Mock
    private InMemoryStorage storage;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTraining() {
        Training training = new Training();
        training.setId(1);
        trainingDAO.create(training);
        verify(storage).addToStorage(EntityType.TRAINING, training);
    }

    @Test
    public void getTraining() {
        Training training = new Training();
        training.setId(1);
        when(storage.getFromStorage(EntityType.TRAINING, 1)).thenReturn(training);
        Training result = trainingDAO.get(1);
        assertEquals(training, result);
    }
}