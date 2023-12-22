package org.example.repository;

import org.example.model.Trainer;
import org.example.model.User;
import org.example.model.TrainingType;
import org.example.storage.InMemoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class TrainerDAOTest {

    @InjectMocks
    private TrainerDAO trainerDAO;

    @Mock
    private InMemoryStorage storage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTrainer() {
        Trainer trainer = new Trainer();
        trainer.setId(1);

        User user = new User();
        user.setId(2);
        trainer.setUser(user);

        TrainingType trainingType = new TrainingType();
        trainingType.setId(3);
        trainer.setTrainingType(trainingType);

        trainerDAO.createTrainer(trainer);

        // Verifying the storage interactions
        verify(storage).addToStorage("users", user.getId(), user);
        verify(storage).addToStorage("trainingTypes", trainingType.getId(), trainingType);
        verify(storage).addToStorage("trainers", trainer.getId(), trainer);
    }

    @Test
    public void testUpdateTrainer() {
        Trainer trainer = new Trainer();
        trainer.setId(1);
        trainerDAO.updateTrainer(trainer);
        verify(storage).updateStorage("trainers", trainer.getId(), trainer);
    }

    @Test
    public void testGetTrainer() {
        Trainer trainer = new Trainer();
        trainer.setId(1);

        when(storage.getFromStorage("trainers", 1)).thenReturn(trainer);

        Trainer result = trainerDAO.getTrainer(1);

        assertEquals(trainer, result);
    }
}
