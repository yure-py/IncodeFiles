package org.example.Study.DAO.Ex1.DAO;

import org.example.Study.DAO.Ex1.DTO.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();
    public User getUser(int id);
    public void updateUser(User user);
    public void deleteUser(User user);
}
