package org.example.service;

import org.example.model.Trainer;
import org.example.repository.TrainerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest {

    @Mock
    private TrainerDAO trainerDAO;

    @InjectMocks
    private TrainerService trainerService;

    private Trainer trainer;

    @BeforeEach
    public void setUp() {
        trainer = new Trainer();
        trainer.setId(1);
    }

    @Test
    public void testCreateTrainerWhenTrainerPassedThenTrainerCreated() {
        // Act
        trainerService.createTrainer(trainer);

        // Assert
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
        // Act
        trainerService.getTrainer(1);

        // Assert
        verify(trainerDAO).getTrainer(1);
    }
}
