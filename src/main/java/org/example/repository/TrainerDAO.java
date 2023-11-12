package org.example.repository;

import org.example.dbconnection.DatabaseConnection;
import org.example.model.Trainer;
import org.example.model.TrainingType;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {
    private final UserDAO userDAO = new UserDAO();
    private final TrainingTypeDAO trainingTypeDAO = new TrainingTypeDAO();

    private static final String INSERT_TRAINER = "INSERT INTO trainer (user_id, specialization_id) VALUES (?, ?)";
    private static final String UPDATE_TRAINER = "UPDATE trainer SET user_id = ?, specialization_id = ? WHERE id = ?";
    private static final String SELECT_TRAINER_BY_ID = "SELECT * FROM trainer WHERE id = ?";
    private static final String SELECT_ALL_TRAINERS = "SELECT * FROM trainer";
    private static final String SELECT_SPECIALIZATION_BY_ID = "SELECT * FROM traning_type WHERE id = ?";

    public void insertTrainer(Trainer trainer) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRAINER)) {
            preparedStatement.setInt(1, trainer.getUser().getId());
            preparedStatement.setInt(2, trainer.getTrainingType().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTrainer(Trainer trainer) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRAINER)) {
            preparedStatement.setInt(1, trainer.getUser().getId());
            preparedStatement.setInt(2, trainer.getTrainingType().getId());
            preparedStatement.setInt(3, trainer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Trainer selectTrainerById(int id) {
        Trainer trainer = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRAINER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trainer = new Trainer();
                trainer.setId(resultSet.getInt("id"));
                int userId = resultSet.getInt("user_id");
                trainer.setUser(userDAO.getUserById(userId));
                int specializationId = resultSet.getInt("specialization_id");
                trainer.setTrainingType(trainingTypeDAO.selectTrainingTypeById(specializationId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainer;
    }

    public List<Trainer> selectAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRAINERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Trainer trainer = new Trainer();
                trainer.setId(resultSet.getInt("id"));
                int userId = resultSet.getInt("user_id");
                trainer.setUser(userDAO.getUserById(userId));
                int specializationId = resultSet.getInt("specialization_id");
                trainer.setTrainingType(trainingTypeDAO.selectTrainingTypeById(specializationId));
                trainers.add(trainer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainers;
    }
}