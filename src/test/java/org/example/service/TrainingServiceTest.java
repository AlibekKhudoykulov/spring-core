package org.example.service;

import org.example.dto.TrainingDto;
import org.example.model.Trainee;
import org.example.model.Trainer;
import org.example.model.Training;
import org.example.model.TrainingType;
import org.example.repository.TraineeDAO;
import org.example.repository.TrainerDAO;
import org.example.repository.TrainingDAO;
import org.example.repository.TrainingTypeDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TrainingServiceTest {

    @InjectMocks
    private TrainingService trainingService;

    @Mock
    private TrainingDAO trainingDAO;

    @Mock
    private TrainerDAO trainerDAO;

    @Mock
    private TraineeDAO traineeDAO;

    @Mock
    private TrainingTypeDAO trainingTypeDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTraining() {
        // Given
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setTrainerId(1);
        trainingDto.setTraineeId(2);
        trainingDto.setTrainingTypeId(3);
        trainingDto.setTrainingName("Test Training");
        trainingDto.setTrainingDate(new Date());
        trainingDto.setTrainingDuration(2.5f);

        Trainer trainer = new Trainer();
        when(trainerDAO.getTrainer(1)).thenReturn(trainer);

        Trainee trainee = new Trainee();
        when(traineeDAO.getTrainee(2)).thenReturn(trainee);

        TrainingType trainingType = new TrainingType();
        when(trainingTypeDAO.getTrainingType(3)).thenReturn(trainingType);

        // When
        trainingService.createTraining(trainingDto);

        // Then
        verify(trainingDAO, times(1)).createTraining(any(Training.class));
    }
    @Test
    public void getTraining() {
        // Given
        int trainingId = 1;
        Training expectedTraining = new Training();
        expectedTraining.setId(trainingId);
        expectedTraining.setTrainingName("Test Training");

        when(trainingDAO.getTraining(trainingId)).thenReturn(expectedTraining);

        // When
        Training actualTraining = trainingService.getTraining(trainingId);

        // Then
        verify(trainingDAO, times(1)).getTraining(trainingId);

        // Additional assertions
        assertNotNull(actualTraining);
        assertEquals(expectedTraining.getId(), actualTraining.getId());
        assertEquals(expectedTraining.getTrainingName(), actualTraining.getTrainingName());
    }

    @Test
    public void getTrainingNotFound() {
        // Given
        int trainingId = 1;

        when(trainingDAO.getTraining(trainingId)).thenReturn(null);

        // When
        Training actualTraining = trainingService.getTraining(trainingId);

        // Then
        verify(trainingDAO, times(1)).getTraining(trainingId);

        // Additional assertions
        assertNull(actualTraining);
    }
}
