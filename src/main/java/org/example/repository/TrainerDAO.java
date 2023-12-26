package org.example.repository;


import org.example.contant.EntityType;
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
        storage.addToStorage(EntityType.USER, trainer.getUser().getId(), trainer.getUser());
        storage.addToStorage(EntityType.TRAINING_TYPE, trainer.getTrainingType().getId(), trainer.getTrainingType());
        storage.addToStorage(EntityType.TRAINER, trainer.getId(), trainer);

    }

    public void updateTrainer(Trainer trainer) {
        storage.updateStorage(EntityType.TRAINER, trainer.getId(), trainer);
    }

    public Trainer getTrainer(int trainerId) {
        return (Trainer) storage.getFromStorage(EntityType.TRAINER, trainerId);
    }
}