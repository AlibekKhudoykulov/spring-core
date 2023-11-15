package org.example.repository;

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
        storage.addToStorage("users", trainee.getUser().getId(), trainee.getUser());

        storage.addToStorage("trainees", trainee.getId(), trainee);
    }

    public void updateTrainee(Trainee trainee) {
        storage.updateStorage("trainees", trainee.getId(), trainee);
    }

    public void deleteTrainee(int traineeId) {
        storage.removeFromStorage("trainees", traineeId);
    }

    public Trainee getTrainee(int traineeId) {
        return (Trainee) storage.getFromStorage("trainees", traineeId);
    }
}

