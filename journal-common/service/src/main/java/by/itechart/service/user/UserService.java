package by.itechart.service.user;

import by.itechart.model.user.User;
import net.bytebuddy.implementation.bytecode.Throw;

import java.util.List;

public interface UserService {

    User getUserById(Long userId) throws Throwable;

    User getUserByUsername(String userName) throws Throwable;

    User saveUser(User user);

    void deleteUserById(Long userId);

    void deleteUserByName(String userName);

    List<User> getAllUsers();

    List<User> getAllUsersByAuthority(String name);

    User updateUser(Long userId, User updateUser) throws Throwable;

    User updateUserByUsername(String userName, User updateUser) throws Throwable;
}
