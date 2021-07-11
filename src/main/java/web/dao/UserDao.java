package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUser(int id);
    List<User> allUsers();

    User getUserByName(String userName);
}
