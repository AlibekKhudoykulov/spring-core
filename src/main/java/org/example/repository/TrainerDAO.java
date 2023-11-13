package org.example.repository;


import org.example.model.Trainer;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDAO {
    private InMemoryStorage storage;

    public TrainerDAO(InMemoryStorage storage) {
        this.storage = storage;
    }
    public void createTrainer(Trainer trainer) {
        storage.addToStorage("trainers", trainer.getId(), trainer);
    }

    public void updateTrainer(Trainer trainer) {
        storage.updateStorage("trainers", trainer.getId(), trainer);
    }

    public Trainer getTrainer(int trainerId) {
        return (Trainer) storage.getFromStorage("trainers", trainerId);
    }
}