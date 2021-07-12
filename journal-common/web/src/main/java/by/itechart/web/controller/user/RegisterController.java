package by.itechart.web.controller.user;


import by.itechart.mapping.dto.user.RegisterUserDto;
import by.itechart.mapping.dto.user.UserDto;
import by.itechart.mapping.user.UserMapper;
import by.itechart.mapping.user.UserMapperWithUserRole;
import by.itechart.model.user.User;
import by.itechart.service.user.UserService;
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

    private final UserMapperWithUserRole customMapper;

    @PostMapping("/")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody
                                                   @Valid RegisterUserDto requestDto) throws Throwable {

        User user = customMapper.registerUserDtoToUserWithRole(requestDto);
        User storedUser = userService.saveUser(user);
        UserDto dto = mapper.userToUserDto(storedUser);

        return new ResponseEntity<>(
                                    dto, HttpStatus.CREATED);
    }
}
