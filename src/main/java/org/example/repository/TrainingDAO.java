package org.example.repository;

import org.example.constant.EntityType;
import org.example.model.Training;
import org.example.repository.template.BaseDAO;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingDAO implements BaseDAO<Training> {
    private InMemoryStorage storage;

    public TrainingDAO(InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public void create(Training entity) {
        storage.addToStorage(EntityType.TRAINING, entity);
    }

    @Override
    public Training get(int id) {
        return (Training) storage.getFromStorage(EntityType.TRAINING, id);
    }
}