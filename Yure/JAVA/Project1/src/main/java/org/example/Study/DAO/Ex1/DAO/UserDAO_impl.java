package org.example.Study.DAO.Ex1.DAO;

import org.example.Study.Connection.Pool;
import org.example.Study.DAO.Ex1.DTO.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_impl implements UserDAO {
    Connection connection = Pool.getConnection();
    Statement stmt;


    String selectAll = "Select * FROM Users";

    public UserDAO_impl() {
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Error ao inicializar! " + e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
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
