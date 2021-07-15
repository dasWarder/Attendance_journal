package by.itechart.web.controller.user;


import by.itechart.mapping.dto.user.NoPassUserDto;
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
@RequestMapping("/admin/users")
public class SuperAdminController {

    private final UserService userService;

    private final UserMapper mapper;

    private final UserMapperWithUserRole customMapper;

    @PostMapping("/user")
    public ResponseEntity<NoPassUserDto> saveUserWithRole(@RequestBody
                                                          @Valid NoPassUserDto userDto) throws Throwable {

        User user = customMapper.noPassUserDtoToUserWithRoleDefaultPass(userDto);
        User storedUser = userService.saveUser(user);
        NoPassUserDto dto = mapper.userToNoPassUserDto(storedUser);

        return new ResponseEntity<>(
                                    dto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<NoPassUserDto> getUserById(@PathVariable("userId")
                                                   @Min(value = 1,
                                                           message = "The ID must be greater than 0")
                                                   Long userId) throws Throwable {

        User userById = userService.getUserById(userId);
        NoPassUserDto dto = mapper.userToNoPassUserDto(userById);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }


    @PutMapping("/user/{userId}")
    public ResponseEntity<NoPassUserDto> updateUser(@PathVariable("userId")
                                                  @Min(value = 1,
                                                          message = "The ID must be greater than 0")
                                                  Long userId,
                                                  @RequestBody
                                                  @Valid NoPassUserDto userDto) throws Throwable {

        User user = customMapper.noPassUserDtoToUserWithRoleAndPass(userDto, userId);
        User updatedUser = userService.updateUser(userId, user);
        NoPassUserDto dto = mapper.userToNoPassUserDto(updatedUser);


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
    public ResponseEntity<List<NoPassUserDto>> getAllUsers(@RequestParam(value = "role", required = false)
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
