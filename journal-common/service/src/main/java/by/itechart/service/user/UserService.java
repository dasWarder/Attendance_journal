package by.itechart.service.user;

import by.itechart.model.user.User;

import java.util.List;

public interface UserService {

    User getUserById(Long userId) throws Throwable;

    User getUserByUsername(String userName) throws Throwable;

    User saveUser(User user) throws Throwable;

    void deleteUserById(Long userId);

    void deleteUserByName(String userName);

    List<User> getAllUsers();

    List<User> getAllUsersByAuthority(String name);

    User updateUser(Long userId, User updateUser) throws Throwable;

    User updateUserByUsername(String userName, User updateUser) throws Throwable;
}
