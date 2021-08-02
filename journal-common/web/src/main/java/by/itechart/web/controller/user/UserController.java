package by.itechart.web.controller.user;

import by.itechart.mapping.dto.user.FullUserDto;
import by.itechart.mapping.dto.user.RegisterUserDto;
import by.itechart.mapping.user.UserMapper;
import by.itechart.mapping.user.UserMapperWithUserRole;
import by.itechart.model.user.User;
import by.itechart.service.user.UserService;
import by.itechart.web.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper mapper;

    private final UserService userService;

    private final UserMapperWithUserRole customMapper;

    private final SecurityUtil securityUtil;

    @GetMapping("/details")
    public ResponseEntity<FullUserDto> getUserInfo() throws Throwable {

        String username = securityUtil.getLoggedUser();

        User userById = userService.getUserByUsername(username);
        FullUserDto dto = mapper.userToFullUserDto(userById);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/details")
    public ResponseEntity<FullUserDto> updateUserInfo(@RequestBody
                                                      @Valid RegisterUserDto passDto) throws Throwable {

        String username = securityUtil.getLoggedUser();

        User updateUser = customMapper.registerUserDtoToUserWithRoleAuth(passDto, username);
        User user = userService.updateUserByUsername(username, updateUser);
        FullUserDto dto = mapper.userToFullUserDto(user);

        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/details")
    public ResponseEntity<Void> deleteUserById() {

        String username = securityUtil.getLoggedUser();

        userService.deleteUserByName(username);

        return ResponseEntity.noContent()
                                        .build();
    }



}
