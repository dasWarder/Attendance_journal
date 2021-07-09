package by.itechart.service.user;

import by.itechart.model.user.User;
import by.itechart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.itechart.model.util.ValidationUtil.validateOptional;
import static by.itechart.model.util.ValidationUtil.validateParams;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public User getUserById(Long userId) throws Throwable {

        validateParams(userId);

        log.info("Receive a user by the ID = {}",
                                                 userId);
        Optional<User> possibleUser = userRepository.findById(userId);
        User validUser = validateOptional(possibleUser, User.class);

        return validUser;
    }

    @Override
    public User getUserByUsername(String userName) throws Throwable {

        validateParams(userName);

        log.info("Receive a user by the name = {}",
                                                   userName);
        Optional<User> possibleUser = userRepository.getUserByUsername(userName);
        User validUser = validateOptional(possibleUser, User.class);

        return validUser;
    }

    @Override
    public User saveUser(User user) {

        validateParams(user);

        log.info("Store a user with the name = {}",
                                                  user.getUsername());
        setPasswordToEncryptedOne(user);
        User storedUser = userRepository.save(user);

        return storedUser;
    }

    @Override
    public void deleteUserById(Long userId) {

        validateParams(userId);

        log.info("Remove a user by the ID = {}",
                                                userId);
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteUserByName(String userName) {

        validateParams(userName);

        log.info("Remove a user by the name = {}",
                                                  userName);
        userRepository.deleteUserByUsername(userName);
    }

    @Override
    public List<User> getAllUsers() {

        log.info("Receive a list of all users");

        List<User> users = (List<User>) userRepository.findAll();

        return users;
    }

    @Override
    public List<User> getAllUsersByAuthority(String name) {

        validateParams(name);

        log.info("Receive a list of all users by the authority name = {}",
                                                                          name);
        List<User> users = userRepository.getUsersByRole_Authority(name);

        return users;
    }

    @Override
    public User updateUser(Long userId, User updateUser) throws Throwable {

        validateParams(userId, updateUser);

        log.info("Update a user with the ID = {}",
                                                  userId);
        User userById = this.getUserById(userId);
        updateUser.setId(userById.getId());
        User updatedUser = userRepository.save(updateUser);

        return updatedUser;
    }

    private void setPasswordToEncryptedOne(User user) {

        String notSecurePass = user.getPassword();

        String encodedPass = encoder.encode(notSecurePass);

        user.setPassword(encodedPass);
    }
}
