package by.itechart.mapping.user;


import by.itechart.mapping.dto.user.UserDto;
import by.itechart.model.user.User;
import by.itechart.model.user.UserAuthority;
import by.itechart.model.util.ValidationUtil;
import by.itechart.repository.AuthorityRepository;
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
    private AuthorityRepository authorityRepository;

    public User userDtoToUserWithUserRole(UserDto dto) throws Throwable {

        log.info("Mapping a user dto to user with a role USER");

        Optional<UserAuthority> possibleAuthority = authorityRepository.getUserAuthorityByAuthority("USER");
        UserAuthority userAuthority = validateOptional(possibleAuthority, UserAuthority.class);

        User user = new User();

        user.setId(dto.getId());
        user.setUsername(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setEnabled(true);
        user.setRole(userAuthority);

        return user;
    }
}
