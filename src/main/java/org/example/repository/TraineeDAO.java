package org.example.repository;

import org.example.constant.EntityType;
import org.example.model.Trainee;
import org.example.repository.template.BaseDAO;
import org.example.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TraineeDAO implements BaseDAO<Trainee> {

    @Autowired
    private InMemoryStorage storage;

    public TraineeDAO(InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public void create(Trainee entity) {
        storage.addToStorage(EntityType.USER, entity.getUser());

        storage.addToStorage(EntityType.TRAINEE, entity);
    }
    @Override
    public Trainee get(int id) {
        return (Trainee) storage.getFromStorage(EntityType.TRAINEE, id);
    }

    public void updateTrainee(Trainee trainee) {
        storage.updateStorage(EntityType.TRAINEE,trainee);
    }

    public void deleteTrainee(int traineeId) {
        storage.removeFromStorage(EntityType.TRAINEE, traineeId);
    }
}

