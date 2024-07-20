package com.astonhome.firsttomcat.repository;

import com.astonhome.firsttomcat.dto.TrainingDTO;
import com.astonhome.firsttomcat.utils.DatabaseConnection;

import java.sql.*;

public class  TrainingDAO {
    public boolean checkTraining(TrainingDTO trainingDTO) {

        String sql = "SELECT 1 FROM users_coaches WHERE user_id = ? and coach_id= ? LIMIT 1";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, trainingDTO.getUser());
            statement.setLong(2, trainingDTO.getCoach());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setTraining(TrainingDTO trainingDTO) {

        String sql = "INSERT INTO users_coaches (user_id, coach_id) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, trainingDTO.getUser());
            statement.setLong(2, trainingDTO.getCoach());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTraining(TrainingDTO trainingDTO) {
        String sql = "DELETE FROM users_coaches WHERE user_id = ? and coach_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, trainingDTO.getUser());
            statement.setLong(2, trainingDTO.getCoach());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}