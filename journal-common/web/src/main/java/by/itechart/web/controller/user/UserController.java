package by.itechart.web.controller.user;


import by.itechart.mapping.dto.user.UserDto;
import by.itechart.mapping.user.UserMapper;
import by.itechart.mapping.user.UserMapperWithUserRole;
import by.itechart.model.user.User;
import by.itechart.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserMapper mapper;

    private final UserMapperWithUserRole customMapper;

    private static final String DELETE_USER_BY_ID_MESSAGE = "The user with ID = %d was successfully deleted";

    private static final String DELETE_USER_BY_USERNAME_MESSAGE = "The user with a name = %s was successfully deleted";


    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser(@RequestBody
                                            @Valid UserDto userDto) throws Throwable {

        User user = customMapper.userDtoToUserWithUserRole(userDto);
        User storedUser = userService.saveUser(user);
        UserDto dto = mapper.userToUserDto(storedUser);

        return new ResponseEntity<>(
                                    dto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId")
                                               @Min(value = 1,
                                                    message = "The ID must be greater than 0")
                                               Long userId)
                                                          throws Throwable {
        User userById = userService.getUserById(userId);
        UserDto dto = mapper.userToUserDto(userById);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam("email")
                                                     @Min(value = 1,
                                                          message = "The size of the email must be not null")
                                                     String email)
                                                                 throws Throwable {

        User userByUsername = userService.getUserByUsername(email);
        UserDto dto = mapper.userToUserDto(userByUsername);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId")
                                              @Min(value = 1,
                                                   message = "The ID must be greater than 0")
                                              Long userId,
                                              @RequestBody
                                              @Valid User updateUser)
                                                                    throws Throwable {
        User user = userService.updateUser(userId, updateUser);
        UserDto dto = mapper.userToUserDto(user);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId")
                                                  @Min(value = 1,
                                                       message = "The ID must be grater than 0")
                                                  Long userId) {

        userService.deleteUserById(userId);

        return ResponseEntity.noContent()
                                        .build();
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUserByUsername(@RequestParam("email")
                                                        @Min(value = 1,
                                                             message = "The email size must be grater than 0")
                                                         String email) {

        userService.deleteUserByName(email);

        return ResponseEntity.noContent()
                                        .build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsersByAuthorityName(@RequestParam(value = "role", required = false)
                                                                    @Min(value = 1,
                                                                         message = "The authority name size must be greater than 0")
                                                                    String authorityName) {

        List<UserDto> response = getAllUserOrAllByAuthority(authorityName);

        return new ResponseEntity<>(
                                    response, HttpStatus.OK);
    }



    private List<UserDto> getAllUserOrAllByAuthority(String authorityName) {

        List<User> users;

        if(authorityName == null) {

            users = userService.getAllUsers();

        } else {

            users = userService.getAllUsersByAuthority(authorityName);

        }

        List<UserDto> responseList = mapper.userListToUserDtoList(users);

        return responseList;
    }


}
