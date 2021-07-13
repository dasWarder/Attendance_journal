package by.itechart.mapping.user;


import by.itechart.mapping.dto.user.*;
import by.itechart.model.user.User;
import by.itechart.model.user.UserAuthority;
import by.itechart.repository.AuthorityRepository;
import by.itechart.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static by.itechart.model.util.ValidationUtil.*;

@Slf4j
@NoArgsConstructor
@Mapper(componentModel = "spring")
public abstract class UserMapperWithUserRole {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    private static final String DEFAULT_PASSWORD = "default";

    public User userDtoToUserWithUserRoleAndDefaultPassword(UserDto dto) throws Throwable {

        log.info("Mapping a user dto to user with a role USER");

        UserAuthority userAuthority = getUserAuthority("USER");

        User user = getUserWithoutPassword(dto, userAuthority);

        user.setEnabled(dto.isEnabled());
        user.setPassword(DEFAULT_PASSWORD);

        return user;
    }

    public User userDtoToUserWithUserRoleAndPassword(UserDto dto, Long userId) throws Throwable {

        log.info("Mapping a user dto to user with a role USER for the update method");

        UserAuthority userAuthority = getUserAuthority("USER");

        User user = getUserWithoutPassword(dto, userAuthority);

        getCurrentUserPasswordAndSaveIt(user, userId);
        user.setEnabled(dto.isEnabled());

        return user;
    }

    public User noPassUserDtoToUserWithRoleDefaultPass(NoPassUserDto userDto) throws Throwable {

        log.info("Mapping a no pass user dto to the user with a role");

        UserAuthority userAuthority = getUserAuthority(userDto.getRole());

        User user = getUserWithoutPassword(userDto, userAuthority);

        user.setId(userDto.getId());
        user.setPassword(DEFAULT_PASSWORD);
        user.setEnabled(user.isEnabled());

        return user;
    }

    public User noPassUserDtoToUserWithRoleAndPass(NoPassUserDto dto, Long userId) throws Throwable {

        log.info("Mapping a no pass user dto to the user with a role");

        UserAuthority userAuthority = getUserAuthority(dto.getRole());

        User user = getUserWithoutPassword(dto, userAuthority);

        getCurrentUserPasswordAndSaveIt(user, userId);
        user.setEnabled(dto.isEnabled());

        return user;
    }

    public User registerUserDtoToUserWithRole(RegisterUserDto dto) throws Throwable {

        log.info("Mapping a register user dto to a user with USER role");

        UserAuthority userRole = getUserAuthority("USER");

        User user = getUserWithoutPassword(dto, userRole);
        user.setPassword(dto.getPassword());
        user.setEnabled(true);

        return user;
    }

    public User registerUserDtoToUserWithRoleAuth(RegisterUserDto dto, String username) throws Throwable {

        log.info("Mapping a user pass dto to the user with USER role");

        UserAuthority userAuthority = getUserAuthority("USER");

        User user = getUserWithoutPassword(dto, userAuthority);

        getCurrentUserPasswordAndSaveIt(user, username);
        getCurrentUserEnableStatusAndSaveIt(user, username);

        return user;
    }





    private <T extends BaseUserDto> User getUserWithoutPassword(T dto,
                                                                UserAuthority userAuthority) {

        User user = new User();

        user.setUsername(dto.getEmail());
        user.setRole(userAuthority);

        return user;

    }

    private UserAuthority getUserAuthority(String role) throws Throwable {

        Optional<UserAuthority> possibleAuthority = getPossibleAuthorityByStringRole(role);

        UserAuthority userAuthority = validateOptional(possibleAuthority, UserAuthority.class);

        return userAuthority;
    }


    private Optional<UserAuthority> getPossibleAuthorityByStringRole(String role) {

        Optional<UserAuthority> possibleUserAuthority;

        switch (role) {

            case "ADMIN":
                possibleUserAuthority = authorityRepository.getUserAuthorityByAuthority("ADMIN");
                break;

            case "SUPER ADMIN":
                possibleUserAuthority = authorityRepository.getUserAuthorityByAuthority("SUPER_ADMIN");
                break;

            default:
                possibleUserAuthority = authorityRepository.getUserAuthorityByAuthority("USER");
        }

        return possibleUserAuthority;
    }

    private <T> void getCurrentUserPasswordAndSaveIt(User user, T param) throws Throwable {

        User userFromDb = getUserByParamType(param);
        user.setPassword(userFromDb.getPassword());

    }

    private <T> void getCurrentUserEnableStatusAndSaveIt(User user, T param) throws Throwable {

        User userFromDb = getUserByParamType(param);
        user.setEnabled(userFromDb.isEnabled());

    }

    private <T> User getUserByParamType(T param) throws Throwable {

        Class<?> paramClass = param.getClass();
        User userFromDb = null;

        if(paramClass == Long.class) {

            userFromDb = getUserByUserId((Long) param);

        } else if (paramClass == String.class) {

            userFromDb = getUserByUsername((String) param);
        }

        return userFromDb;
    }


    private User getUserByUserId(Long userId) throws Throwable {

        Optional<User> possibleUser = userRepository.findById(userId);
        User userFromDb = validateOptional(possibleUser, User.class);

        return userFromDb;
    }

    private User getUserByUsername(String username) throws Throwable {

        Optional<User> possibleUser = userRepository.getUserByUsername(username);
        User userFromDb = validateOptional(possibleUser, User.class);

        return userFromDb;
    }

}
