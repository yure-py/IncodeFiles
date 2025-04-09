package org.example.Study.DAO.Ex1.DAO;

import org.example.Study.Connection.Pool;
import org.example.Study.DAO.Ex1.DTO.User;
import org.example.Study.DAO.Ex1.DatabaseUtils.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_impl implements UserDAO {
    Connection connection = Pool.getConnection();

    private final String TB_NAME = "Users";

    // -------- SQLs --------
    String selectAll = "Select * FROM " + TB_NAME;
    String insert = String.format("""
            INSERT INTO %s (id, name, email, timestamp)
            VALUES (default, ?, ?, default)
            """, TB_NAME);
    // -------- END --------

    @Override
    public List<User> getAllUsers() {
        try (
            Statement stmt = connection.createStatement()
        ) {
            List<User> results = new ArrayList<>();

            ResultSet result = stmt.executeQuery(selectAll);

            while (result.next()) {
                System.out.println(result.getInt("id"));
                System.out.println(result.getString("name"));
                System.out.println(result.getString("email"));
            }

            return List.of();
        } catch (SQLException e) {
            throw new RuntimeException("Error ao buscar registros! " + e);
        }
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
