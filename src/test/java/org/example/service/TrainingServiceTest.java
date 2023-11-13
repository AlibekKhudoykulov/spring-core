package org.example.service;

import org.example.model.Training;
import org.example.model.TrainingType;
import org.example.repository.TrainingDAO;
import org.example.repository.TrainingTypeDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceTest {

    @Mock
    private TrainingDAO trainingDAO;

    @Mock
    private TrainingTypeDAO trainingTypeDAO;

    @InjectMocks
    private TrainingService trainingService;

    @Test
    public void testCreateTrainingWhenTrainingIsCreatedThenSuccess() {
        // Arrange
        Training training = new Training();

        // Act
        trainingService.createTraining(training);

        // Assert
        verify(trainingDAO, times(1)).createTraining(training);
    }

    @Test
    public void testGetTrainingWhenTrainingExistsThenReturnTraining() {
        // Arrange
        Training training = new Training();
        when(trainingDAO.getTraining(anyInt())).thenReturn(training);

        // Act
        Training result = trainingService.getTraining(1);

        // Assert
        verify(trainingDAO, times(1)).getTraining(1);
        assertSame(training, result);
    }

    @Test
    public void testCreateTrainingTypeWhenTrainingTypeIsCreatedThenSuccess() {
        // Arrange
        TrainingType trainingType = new TrainingType();

        // Act
        trainingService.createTrainingType(trainingType);

        // Assert
        verify(trainingTypeDAO, times(1)).createTrainingType(trainingType);
    }

    @Test
    public void testGetTrainingTypeWhenTrainingTypeExistsThenReturnTrainingType() {
        // Arrange
        TrainingType trainingType = new TrainingType();
        when(trainingTypeDAO.getTrainingType(anyInt())).thenReturn(trainingType);

        // Act
        TrainingType result = trainingService.getTrainingType(1);

        // Assert
        verify(trainingTypeDAO, times(1)).getTrainingType(1);
        assertSame(trainingType, result);
    }
}
