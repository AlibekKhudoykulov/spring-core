package org.example.repository;

import org.example.dbconnection.DatabaseConnection;
import org.example.model.Trainer;
import org.example.model.Training;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainingDAO {
    private final TrainerDAO trainerDAO = new TrainerDAO();
    private final TraineeDAO traineeDAO = new TraineeDAO();


    private static final String INSERT_TRAINING = "INSERT INTO training (trainee_id, trainer_id, training_name, training_type_id, training_date, training_duration) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_TRAINING_BY_ID = "SELECT * FROM training WHERE id = ?";
    private static final String SELECT_ALL_TRAININGS = "SELECT * FROM training";

    public void insertTraining(Training training) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRAINING)) {
            preparedStatement.setInt(1, training.getTrainee().getId());
            preparedStatement.setInt(2, training.getTrainer().getId());
            preparedStatement.setString(3, training.getTrainingName());
            preparedStatement.setInt(4, training.getTrainingTypeId());
            preparedStatement.setDate(5, (Date) training.getTrainingDate());
            preparedStatement.setFloat(6, training.getTrainingDuration());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Training selectTrainingById(int id) {
        Training training = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRAINING_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                training = new Training();
                training.setId(resultSet.getInt("id"));
                int traineeId = resultSet.getInt("trainee_id");
                training.setTrainee(traineeDAO.selectTraineeById(traineeId));
                int trainerId = resultSet.getInt("trainer_id");
                training.setTrainer(trainerDAO.selectTrainerById(trainerId));
                training.setTrainingName(resultSet.getString("training_name"));
                training.setTrainingTypeId(resultSet.getInt("training_type_id"));
                training.setTrainingDate(resultSet.getDate("training_date"));
                training.setTrainingDuration(resultSet.getFloat("training_duration"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return training;
    }

    public List<Training> selectAllTrainings() {
        List<Training> trainings = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRAININGS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Training training = new Training();
                training.setId(resultSet.getInt("id"));
                int traineeId = resultSet.getInt("trainee_id");
                training.setTrainee(traineeDAO.selectTraineeById(traineeId));
                int trainerId = resultSet.getInt("trainer_id");
                training.setTrainer(trainerDAO.selectTrainerById(trainerId));
                training.setTrainingName(resultSet.getString("training_name"));
                training.setTrainingTypeId(resultSet.getInt("training_type_id"));
                training.setTrainingDate(resultSet.getDate("training_date"));
                training.setTrainingDuration(resultSet.getFloat("training_duration"));
                trainings.add(training);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainings;
    }
}