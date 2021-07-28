package by.itechart.service.user;

import by.itechart.model.exception.UserAlreadyExistException;
import by.itechart.model.user.User;
import by.itechart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public User getUserById(Long userId) throws Throwable {

        validateParams(userId);

        log.info("Receive a user by the ID = {}",
                                                 userId);
        Optional<User> possibleUser = userRepository.findById(userId);
        User validUser = validateOptional(possibleUser, User.class);

        return validUser;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String userName) throws Throwable {

        validateParams(userName);

        log.info("Receive a user by the name = {}",
                                                   userName);
        Optional<User> possibleUser = userRepository.getUserByUsername(userName);
        User validUser = validateOptional(possibleUser, User.class);

        return validUser;
    }

    @Override
    @Transactional
    public User saveUser(User user) throws Throwable {

        validateParams(user);
        checkUserAlreadyExistOrThrowException(user);

        log.info("Store a user with the name = {}",
                                                  user.getUsername());
        setPasswordToEncryptedOne(user);

        User storedUser = userRepository.save(user);

        return storedUser;
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {

        validateParams(userId);

        log.info("Remove a user by the ID = {}",
                                                userId);
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void deleteUserByName(String userName) {

        validateParams(userName);

        log.info("Remove a user by the name = {}",
                                                  userName);
        userRepository.deleteUserByUsername(userName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {

        log.info("Receive a list of all users");

        List<User> users = (List<User>) userRepository.findAll();

        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsersByAuthority(String name) {

        validateParams(name);

        log.info("Receive a list of all users by the authority name = {}",
                                                                          name);
        List<User> users = userRepository.getUsersByRole_Authority(name);

        return users;
    }

    @Override
    @Transactional
    public User updateUser(Long userId, User updateUser) throws Throwable {

        validateParams(userId, updateUser);

        log.info("Update a user with the ID = {}",
                                                  userId);
        User userById = this.getUserById(userId);
        updateUser.setId(userById.getId());
        User updatedUser = userRepository.save(updateUser);

        return updatedUser;
    }

    @Override
    @Transactional
    public User updateUserByUsername(String userName, User updateUser) throws Throwable {

        validateParams(userName, updateUser);

        log.info("Update a user with the name = {}",
                                                    userName);
        User userByUsername = this.getUserByUsername(userName);
        updateUser.setId(userByUsername.getId());
        User updatedUser = userRepository.save(updateUser);

        return updatedUser;
    }


    private void setPasswordToEncryptedOne(User user) {

        String notSecurePass = user.getPassword();

        String encodedPass = encoder.encode(notSecurePass);

        user.setPassword(encodedPass);
    }

    private void checkUserAlreadyExistOrThrowException(User user) throws UserAlreadyExistException {

        try {

            User userByUsername = getUserByUsername(user.getUsername());

        } catch (Throwable throwable) {

            return;
        }

        throw new UserAlreadyExistException("The user with this email already exist!");
    }
}
