package by.itechart.web.controller.user;


import by.itechart.mapping.dto.user.NoPassUserDto;
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
public class AdminController {

    private final UserService userService;

    private final UserMapper mapper;

    private final UserMapperWithUserRole customMapper;


    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser(@RequestBody
                                            @Valid UserDto userDto) throws Throwable {

        User user = customMapper.userDtoToUserWithUserRoleAndDefaultPassword(userDto);
        User storedUser = userService.saveUser(user);
        UserDto dto = mapper.userToUserDto(storedUser);

        return new ResponseEntity<>(
                                    dto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<NoPassUserDto> getUserById(@PathVariable("userId")
                                               @Min(value = 1,
                                                    message = "The ID must be greater than 0")
                                               Long userId)
                                                          throws Throwable {
        User userById = userService.getUserById(userId);
        NoPassUserDto dto = mapper.userToNoPassUserDto(userById);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<NoPassUserDto> getUserByUsername(@RequestParam("email")
                                                     @Min(value = 1,
                                                          message = "The size of the email must be not null")
                                                     String email)
                                                                 throws Throwable {

        User userByUsername = userService.getUserByUsername(email);
        NoPassUserDto dto = mapper.userToNoPassUserDto(userByUsername);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId")
                                              @Min(value = 1,
                                                   message = "The ID must be greater than 0")
                                              Long userId,
                                              @RequestBody
                                              @Valid UserDto userDto)
                                                                    throws Throwable {

        User updateUser = customMapper.userDtoToUserWithUserRoleAndPassword(userDto, userId);
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
    public ResponseEntity<List<NoPassUserDto>> getAllUsersByAuthorityName(@RequestParam(value = "role", required = false)
                                                                    @Min(value = 1,
                                                                         message = "The authority name size must be greater than 0")
                                                                    String authorityName) {

        List<NoPassUserDto> response = getAllUserOrAllByAuthority(authorityName);

        return new ResponseEntity<>(
                                    response, HttpStatus.OK);
    }



    private List<NoPassUserDto> getAllUserOrAllByAuthority(String authorityName) {

        List<User> users;

        if(authorityName == null) {

            users = userService.getAllUsers();

        } else {

            users = userService.getAllUsersByAuthority(authorityName);

        }

        List<NoPassUserDto> responseList = mapper.userListToNoPassUserList(users);

        return responseList;
    }


}
