package org.example.Study.DAO.Ex1.DatabaseUtils;

import org.example.Study.Connection.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Utils {
    static private final String createDatabase = "CREATE DATABASE IF NOT EXISTS ?";
    static private final String createTable = "CREATE TABLE IF NOT EXISTS ?";
    static private final String useTable = "USE users";

    static private final Connection db_connection = Pool.getConnection();

    static private void executeCreateRoutine(PreparedStatement preparedStatement, String name) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void createDatabase(String dbName) throws SQLException {
        try {
            PreparedStatement stmt = db_connection.prepareStatement(createDatabase);
            executeCreateRoutine(stmt, dbName);
        } catch (SQLException e) {
            throw new RuntimeException("Error na criação " + e);
        }
    }

    public void createTable(String tbName) throws SQLException {
        try {
            PreparedStatement stmt = db_connection.prepareStatement(createTable);
            executeCreateRoutine(stmt, tbName);
        } catch (SQLException e) {
            throw new RuntimeException("Error na criação " + e);
        }
    }

    public void useTable() {
        try {
            db_connection.createStatement().execute(useTable);
        } catch (SQLException e) {
            throw new RuntimeException("Error ao usar a tabela " + e);
        }
    }

    static public void verifyExists() {

    }
}
