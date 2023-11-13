package org.example.repository;

import org.example.model.TrainingType;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingTypeDAO {
    private InMemoryStorage storage;

    public TrainingTypeDAO(InMemoryStorage storage) {
        this.storage = storage;
    }

    public void createTrainingType(TrainingType trainingType) {
        storage.addToStorage("trainingTypes", trainingType.getId(), trainingType);
    }

    public TrainingType getTrainingType(int trainingTypeId) {
        return (TrainingType) storage.getFromStorage("trainingTypes", trainingTypeId);
    }
}
