package org.example.repository;


import org.example.model.Trainer;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDAO {
    private final InMemoryStorage storage;

    public TrainerDAO(InMemoryStorage storage) {
        this.storage = storage;
    }
    public void createTrainer(Trainer trainer) {
        storage.addToStorage("users", trainer.getUser().getId(), trainer.getUser());
        storage.addToStorage("trainingTypes", trainer.getTrainingType().getId(), trainer.getTrainingType());
        storage.addToStorage("trainers", trainer.getId(), trainer);

    }

    public void updateTrainer(Trainer trainer) {
        storage.updateStorage("trainers", trainer.getId(), trainer);
    }

    public Trainer getTrainer(int trainerId) {
        return (Trainer) storage.getFromStorage("trainers", trainerId);
    }
}