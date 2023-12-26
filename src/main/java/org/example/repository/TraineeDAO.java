package org.example.repository;

import org.example.contant.EntityType;
import org.example.model.Trainee;
import org.example.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TraineeDAO {

    @Autowired
    private InMemoryStorage storage;

    public TraineeDAO(InMemoryStorage storage) {
        this.storage = storage;
    }

    public void createTrainee(Trainee trainee) {
        storage.addToStorage(EntityType.USER, trainee.getUser().getId(), trainee.getUser());

        storage.addToStorage(EntityType.TRAINEE, trainee.getId(), trainee);
    }

    public void updateTrainee(Trainee trainee) {
        storage.updateStorage(EntityType.TRAINEE, trainee.getId(), trainee);
    }

    public void deleteTrainee(int traineeId) {
        storage.removeFromStorage(EntityType.TRAINEE, traineeId);
    }

    public Trainee getTrainee(int traineeId) {
        return (Trainee) storage.getFromStorage(EntityType.TRAINEE, traineeId);
    }
}

