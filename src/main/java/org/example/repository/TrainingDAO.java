package org.example.repository;

import org.example.model.Training;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingDAO {
    private InMemoryStorage storage;

    public TrainingDAO(InMemoryStorage storage) {
        this.storage = storage;
    }

    public void createTraining(Training training) {
        storage.addToStorage("trainings", training.getId(), training);
    }

    public Training getTraining(int trainingId) {
        return (Training) storage.getFromStorage("trainings", trainingId);
    }
}