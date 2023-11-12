package org.example.repository;

import org.example.dbconnection.DatabaseConnection;
import org.example.model.Trainer;
import org.example.model.TrainingType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingTypeDAO {
    private static final String SELECT_TRAINING_TYPE_BY_ID = "SELECT * FROM training_type WHERE id = ?";

    public TrainingType selectTrainingTypeById(int id) {
        TrainingType trainingType = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRAINING_TYPE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trainingType = new TrainingType();
                trainingType.setId(resultSet.getInt("id"));
                trainingType.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainingType;
    }
}
