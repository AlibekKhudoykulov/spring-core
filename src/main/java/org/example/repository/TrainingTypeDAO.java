package org.example.repository;

import org.example.contant.EntityType;
import org.example.model.TrainingType;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingTypeDAO {
    private final InMemoryStorage storage;

    public TrainingTypeDAO(InMemoryStorage storage) {
        this.storage = storage;
    }

    public TrainingType getTrainingType(int trainingTypeId) {
        return (TrainingType) storage.getFromStorage(EntityType.TRAINING_TYPE, trainingTypeId);
    }
}
