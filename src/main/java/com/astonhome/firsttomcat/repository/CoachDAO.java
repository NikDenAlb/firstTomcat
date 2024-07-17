package com.astonhome.firsttomcat.repository;

import com.astonhome.firsttomcat.entity.Coach;
import com.astonhome.firsttomcat.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoachDAO {
    public static List<Coach> getAllCoaches() {
        List<Coach> coaches = new ArrayList<>();
        String sql = "SELECT * FROM coaches";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                coaches.add(buildCoach(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coaches;
    }

    public static Coach getCoach(long id) {
        Coach coach = null;
        String sql = "SELECT * FROM coaches WHERE coach_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    coach = buildCoach(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coach;
    }

    public static Coach addCoach(Coach coach) {
        String sql = "INSERT INTO coaches (name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, coach.getName());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        coach.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coach;
    }

    public static Coach updateCoach(long id, Coach coach) {
        String sql = "UPDATE coaches SET name = ? WHERE coach_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, coach.getName());
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coach;
    }

    public static Coach deleteCoach(long id) {
        Coach coach = getCoach(id);
        if (coach != null) {
            String sql = "DELETE FROM coaches WHERE coach_id = ?";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setLong(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return coach;
    }

    public static List<Coach> getAllCoachByUserId(long id) {
        List<Coach> coaches = new ArrayList<>();
        String sql = "SELECT c.coach_id, c.name FROM coaches c INNER JOIN users_coaches uc on c.coach_id = uc.coach_id WHERE uc.user_id = ?";


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    coaches.add(buildCoach(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coaches;
    }

    private static Coach buildCoach(ResultSet resultSet) throws SQLException {
        Coach coach = new Coach();
        coach.setId(resultSet.getLong("coach_id"));
        coach.setName(resultSet.getString("name"));
        return coach;
    }
}