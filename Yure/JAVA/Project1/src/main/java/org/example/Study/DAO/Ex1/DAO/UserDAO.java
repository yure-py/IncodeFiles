package org.example.Study.DAO.Ex1.DAO;

import org.example.Study.DAO.Ex1.DTO.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> getAllUsers();
    Optional<User> getUser(int id);
    void insertUser(User user);
    void insertAll(List<User> userList);
    void deleteUser(User user);
    void updateUser(User user);
}
