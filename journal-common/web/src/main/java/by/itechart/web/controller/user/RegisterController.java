package by.itechart.web.controller.user;


import by.itechart.mapping.dto.token.Token;
import by.itechart.mapping.dto.user.RegisterUserDto;
import by.itechart.mapping.dto.user.UserDto;
import by.itechart.mapping.token.TokenMapper;
import by.itechart.mapping.user.UserMapper;
import by.itechart.mapping.user.UserMapperWithUserRole;
import by.itechart.model.user.User;
import by.itechart.service.user.UserService;
import by.itechart.web.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserMapper mapper;

    private final UserService userService;

    private final TokenMapper tokenMapper;

    private final UserMapperWithUserRole customMapper;

    private final TokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody
                                                   @Valid RegisterUserDto requestDto) throws Throwable {

        User user = customMapper.registerUserDtoToUserWithRole(requestDto);
        User storedUser = userService.saveUser(user);
        UserDto dto = mapper.userToUserDto(storedUser);

        return new ResponseEntity<>(
                                    dto, HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public ResponseEntity<Token> auth(@RequestBody
                                        @Valid RegisterUserDto dto) throws Throwable {

        User userFromDto = customMapper.registerUserDtoToUserWithRole(dto);
        User userByUsername = userService.getUserByUsername(userFromDto.getUsername());
        String token = tokenProvider.generateToken(userByUsername.getUsername());
        Token response = tokenMapper.fromStringToToken(token);

        return new ResponseEntity<>(
                                    response, HttpStatus.CREATED);
    }
}
