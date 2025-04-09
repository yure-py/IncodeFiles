package org.example.Study.DAO.Ex1.DAO;

import org.example.Study.Connection.Pool;
import org.example.Study.DAO.Ex1.DTO.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO_impl implements UserDAO {
    Connection connection = Pool.getConnection();

    private final String TB_NAME = "Users";

    // -------- SQLs --------
    String selectAll = "SELECT * FROM " + TB_NAME;
    String selectById = "SELECT * FROM " + TB_NAME + " WHERE id = ?";
    String insert = "INSERT INTO " + TB_NAME + " (id, name, email, default) VALUES (default, ?, ?, default)";
    String update = "UPDATE " + TB_NAME + " SET name = ?, email = ? WHERE id = ?";
    String delete = "DELETE FROM " + TB_NAME + " WHERE id = ?";
    String insertAllPartial = "INSERT INTO " + TB_NAME + " (id, name, email, created_at) VALUES ";
    // -------- END --------

    @Override
    public List<User> getAllUsers() {
        // Tem como usar um iterator para se utilizar do on-demanding e não explodir a memória do computador?
        try (
            Statement stmt = connection.prepareStatement(selectAll)
        ) {
            List<User> results = new ArrayList<>();

            ResultSet result = stmt.executeQuery(selectAll);

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                Timestamp timestamp = result.getTimestamp("created_at");

                results.add(new User(id, name, email, timestamp));
            }
            return results;
        }
        catch (SQLException e) {
            throw new RuntimeException("Error ao buscar registros! " + e);
        }
    }

    @Override
    public Optional<User> getUser(int id) {
        try (
            PreparedStatement stmt = connection.prepareStatement(selectById)
        ) {
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Timestamp timestamp = resultSet.getTimestamp("created_at");

                return Optional.of(new User(id, name, email, timestamp));
            } else {
                return Optional.empty();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertUser(User user) {
        try (
            PreparedStatement stmt = connection.prepareStatement(insert)
        ) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());

            stmt.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException("Error ao inserir os dados!" + e);
        }
    }

    @Override
    public void insertAll(List<User> userList) {
        StringBuilder valuesBuilder = new StringBuilder();

        for (int i = 0; i < userList.size(); i++) {
            if (i > 0)
                valuesBuilder.append(", ");
            valuesBuilder.append("(default, ?, ?, default)");
        }

        String completeQuery = insertAllPartial += valuesBuilder;

        try (
            PreparedStatement stmt = connection.prepareStatement(completeQuery)
        ) {
            int index = 1;
            for (User user : userList) {
                stmt.setString(index++, user.getName());
                stmt.setString(index++, user.getEmail());
            }
            stmt.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException("Error ao inserir todos os registros!" + e);
        }
    }

    @Override
    public void updateUser(User user) {
        try (
            PreparedStatement stmt = connection.prepareStatement(update)
        ) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());

            stmt.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(User user) {
        try (
            PreparedStatement stmt = connection.prepareStatement(delete)
        ) {
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
