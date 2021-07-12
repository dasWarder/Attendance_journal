package by.itechart.web.controller.user;

import by.itechart.mapping.dto.user.FullUserDto;
import by.itechart.mapping.dto.user.UserPassDto;
import by.itechart.mapping.user.UserMapper;
import by.itechart.mapping.user.UserMapperWithUserRole;
import by.itechart.model.user.User;
import by.itechart.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper mapper;

    private final UserService userService;

    private final UserMapperWithUserRole customMapper;

    @GetMapping("/details")
    public ResponseEntity<FullUserDto> getUserInfo() throws Throwable {

        String username = getLoggedUser();
        User userById = userService.getUserByUsername(username);
        FullUserDto dto = mapper.userToFullUserDto(userById);

        return new ResponseEntity<>(
                dto, HttpStatus.OK);
    }

    @PutMapping("/details")
    public ResponseEntity<FullUserDto> updateUserInfo(@RequestBody
                                                      @Valid UserPassDto passDto) throws Throwable {

        String userName = getLoggedUser();

        User updateUser = customMapper.userPassDtoToUserWithRole(passDto, userName);
        User user = userService.updateUserByUsername(userName, updateUser);
        FullUserDto dto = mapper.userToFullUserDto(user);

        return new ResponseEntity<>(
                dto, HttpStatus.OK);
    }


    @DeleteMapping("/details")
    public ResponseEntity<Void> deleteUserById() {

        String username = getLoggedUser();
        userService.deleteUserByName(username);

        return ResponseEntity.noContent()
                .build();
    }


    private String getLoggedUser() {
        UserDetails customer = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return customer.getUsername();
    }
}
