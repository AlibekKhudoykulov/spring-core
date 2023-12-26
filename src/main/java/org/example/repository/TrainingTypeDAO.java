package org.example.repository;

import org.example.constant.EntityType;
import org.example.model.TrainingType;
import org.example.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingTypeDAO {
    private InMemoryStorage storage;

    @Autowired
    public void setStorage(InMemoryStorage storage) {
        this.storage = storage;
    }

    public TrainingType getTrainingType(int trainingTypeId) {
        return (TrainingType) storage.getFromStorage(EntityType.TRAINING_TYPE, trainingTypeId);
    }
}
