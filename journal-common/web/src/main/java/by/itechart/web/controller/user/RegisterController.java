package by.itechart.web.controller.user;


import by.itechart.mapping.dto.token.Token;
import by.itechart.mapping.dto.token.TokenRefreshRequest;
import by.itechart.mapping.dto.user.RegisterUserDto;
import by.itechart.mapping.dto.user.UserDto;
import by.itechart.mapping.token.TokenMapper;
import by.itechart.mapping.user.UserMapper;
import by.itechart.mapping.user.UserMapperWithUserRole;
import by.itechart.model.refreshToken.RefreshToken;
import by.itechart.model.user.User;
import by.itechart.service.refreshToken.RefreshTokenService;
import by.itechart.service.security.UserDetailsSecurityService;
import by.itechart.service.user.UserService;
import by.itechart.web.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserMapper mapper;

    private final UserService userService;

    private final TokenMapper tokenMapper;

    private final UserMapperWithUserRole customMapper;

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsSecurityService securityService;

    private final RefreshTokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody
                                                   @Valid RegisterUserDto requestDto) throws Throwable {

        User user = customMapper.registerUserDtoToUserWithRole(requestDto);
        User storedUser = userService.saveUser(user);
        UserDto dto = mapper.userToUserDto(storedUser);

        return ResponseEntity.created(URI.create("/register"))
                                            .body(dto);
    }

    @PostMapping("/auth")
    public ResponseEntity<Token> auth(@RequestBody
                                        @Valid RegisterUserDto dto) throws Throwable {

        authenticationManager.authenticate(
                                           new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        UserDetails details = securityService.loadUserByUsername(dto.getEmail());
        String token = tokenProvider.generateToken(details.getUsername());

        RefreshToken refreshToken = tokenService.createRefreshToken(details.getUsername());
        Token response = tokenMapper.fromStringsToToken(token, refreshToken.getToken());

        return ResponseEntity.created(URI.create("/auth"))
                                            .body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Token> refreshToken(@RequestBody
                                              @Valid TokenRefreshRequest requestDto) throws Throwable {

        String refreshToken = requestDto.getRefreshToken();

        RefreshToken tokenFromDb = tokenService.findByToken(refreshToken);
        RefreshToken validToken = tokenService.verifyExpiration(tokenFromDb);

        String subject = validToken.getSubject();
        String token = tokenProvider.generateToken(subject);

        Token responseToken = tokenMapper.fromStringsToToken(token, validToken.getToken());

        return ResponseEntity.ok(responseToken);
    }
}
