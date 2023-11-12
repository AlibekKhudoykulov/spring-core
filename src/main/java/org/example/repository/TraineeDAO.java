package org.example.repository;

import org.example.dbconnection.DatabaseConnection;
import org.example.model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TraineeDAO {
    private UserDAO userDAO = new UserDAO();

    private static final String INSERT_TRAINEE = "INSERT INTO trainee (user_id, date_of_birth, address) VALUES (?, ?, ?)";
    private static final String UPDATE_TRAINEE = "UPDATE trainee SET user_id = ?, date_of_birth = ?, address = ? WHERE id = ?";
    private static final String SELECT_TRAINEE_BY_ID = "SELECT * FROM trainee WHERE id = ?";
    private static final String SELECT_ALL_TRAINEES = "SELECT * FROM trainee";
    private static final String DELETE_TRAINEE = "DELETE FROM trainee WHERE id = ?";

    public void insertTrainee(Trainee trainee) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRAINEE)) {
            preparedStatement.setInt(1, trainee.getUser().getId());
            preparedStatement.setDate(2, (Date) trainee.getDateOfBirth());
            preparedStatement.setString(3, trainee.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTrainee(Trainee trainee) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRAINEE)) {
            preparedStatement.setInt(1, trainee.getUser().getId());
            preparedStatement.setDate(2, (Date) trainee.getDateOfBirth());
            preparedStatement.setString(3, trainee.getAddress());
            preparedStatement.setInt(4, trainee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Trainee selectTraineeById(int id) {
        Trainee trainee = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRAINEE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                trainee = new Trainee();
                trainee.setId(resultSet.getInt("id"));
                int userId = resultSet.getInt("user_id");
                trainee.setUser(userDAO.getUserById(userId));
                trainee.setDateOfBirth(resultSet.getDate("date_of_birth"));
                trainee.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainee;
    }

    public List<Trainee> selectAllTrainees() {
        List<Trainee> trainees = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRAINEES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Trainee trainee = new Trainee();
                trainee.setId(resultSet.getInt("id"));
                int userId = resultSet.getInt("user_id");
                trainee.setUser(userDAO.getUserById(userId));
                trainee.setDateOfBirth(resultSet.getDate("date_of_birth"));
                trainee.setAddress(resultSet.getString("address"));
                trainees.add(trainee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainees;
    }

    public void deleteTrainee(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRAINEE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}