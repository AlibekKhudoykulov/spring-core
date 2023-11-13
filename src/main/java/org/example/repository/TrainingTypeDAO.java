package org.example.repository;

import org.example.dbconnection.DatabaseConnection;
import org.example.model.Trainer;
import org.example.model.TrainingType;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TrainingTypeDAO {
    private InMemoryStorage storage;

    public TrainingTypeDAO(InMemoryStorage storage) {
        this.storage = storage;
    }

    public void createTrainingType(TrainingType trainingType) {
        storage.addToStorage("trainingTypes", trainingType.getId(), trainingType);
    }

    public TrainingType getTrainingType(int trainingTypeId) {
        return (TrainingType) storage.getFromStorage("trainingTypes", trainingTypeId);
    }
}
