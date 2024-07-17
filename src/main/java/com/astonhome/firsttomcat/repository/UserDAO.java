package com.astonhome.firsttomcat.repository;

import com.astonhome.firsttomcat.entity.Coach;
import com.astonhome.firsttomcat.entity.User;
import com.astonhome.firsttomcat.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User getUser(long id) {
        User user = null;
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT * FROM coaches c WHERE c.coach_id in (SELECT coach_id FROM users_coaches WHERE user_id = ?)";
        List<Coach> coaches = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Coach coach = new Coach();
                    coach.setId(resultSet.getLong("id"));
                    coach.setName(resultSet.getString("name"));
                    coaches.add(coach);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.setCoaches(coaches);
        return user;
    }

    public static User addUser(User user) {
        String sql = "INSERT INTO users (name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getName());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User updateUser(long id, User user) {
        String sql = "UPDATE users SET name = ? WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User deleteUser(long id) {
        User user = getUser(id);
        if (user != null) {
            String sql = "DELETE FROM users WHERE user_id = ?";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setLong(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    private static User createUser(ResultSet resultSet) {
        User user;
        try {
            user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    private static User constructUser(ResultSet resultSet, Long id) {
        User user = createUser(resultSet);
        List<Coach> coaches = CoachDAO.getAllCoachByUserId(id);
        user.setCoaches(coaches);

        return user;
    }

    public static List<User> getAllUserByCoachId(long id) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.user_id, u.name FROM users u INNER JOIN users_coaches uc on u.user_id = uc.user_id WHERE uc.coach_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(buildUser(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setName(resultSet.getString("name"));
        return user;
    }
}