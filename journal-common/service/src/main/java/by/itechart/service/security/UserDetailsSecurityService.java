package by.itechart.service.security;

import by.itechart.model.user.User;
import by.itechart.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsSecurityService implements UserDetailsService {

    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String name) {

        User userByUsername = null;


        try {

            userByUsername = userService.getUserByUsername(name);

        } catch (Throwable throwable) {

            throwable.printStackTrace();

        }

        return new CustomUserDetails(userByUsername);
    }
}
