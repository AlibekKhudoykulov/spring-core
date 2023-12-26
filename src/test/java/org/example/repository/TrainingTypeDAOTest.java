package org.example.repository;

import org.example.constant.EntityType;
import org.example.model.TrainingType;
import org.example.storage.InMemoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrainingTypeDAOTest {
    @Mock
    private InMemoryStorage storage;

    @InjectMocks
    private TrainingTypeDAO trainingTypeDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTrainingType() {
        TrainingType mockTrainingType= new TrainingType();
        when(storage.getFromStorage(EntityType.TRAINING_TYPE, 1)).thenReturn(mockTrainingType);

        TrainingType result=trainingTypeDAO.getTrainingType(1);

        assertEquals(mockTrainingType, result);
        verify(storage).getFromStorage(EntityType.TRAINING_TYPE, 1);
    }

    @Test
    public void getTrainingTypeWhenIdIsNotPresentInStorage() {
        when(storage.getFromStorage(EntityType.TRAINING_TYPE, 1)).thenReturn(null);

        TrainingType result = trainingTypeDAO.getTrainingType(1);

        assertNull(result);
        verify(storage).getFromStorage(EntityType.TRAINING_TYPE, 1);
    }
}

