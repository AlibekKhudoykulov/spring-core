package org.example.repository;


import org.example.constant.EntityType;
import org.example.model.Trainer;
import org.example.repository.template.BaseDAO;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDAO implements BaseDAO<Trainer> {
    private final InMemoryStorage storage;

    public TrainerDAO(InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public void create(Trainer entity) {
        storage.addToStorage(EntityType.USER, entity.getUser());
        storage.addToStorage(EntityType.TRAINING_TYPE, entity.getTrainingType());
        storage.addToStorage(EntityType.TRAINER, entity);
    }

    @Override
    public Trainer get(int id) {
        return (Trainer) storage.getFromStorage(EntityType.TRAINER, id);
    }

    public void updateTrainer(Trainer trainer) {
        storage.updateStorage(EntityType.TRAINER, trainer);
    }
}