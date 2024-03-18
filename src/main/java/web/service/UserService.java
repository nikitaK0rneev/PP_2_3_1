package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);

    void removeUserById(int id);

    void editUser(User user);

    User find(int id);
}
