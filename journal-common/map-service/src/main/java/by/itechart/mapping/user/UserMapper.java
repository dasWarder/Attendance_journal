package by.itechart.mapping.user;


import by.itechart.mapping.dto.user.*;
import by.itechart.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "email")
    UserDto userToUserDto(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    User userDtoToUser(UserDto userDto);

    @Mapping(source = "username", target = "email")
    @Mapping(source = "role.authority", target = "role")
    NoPassUserDto userToNoPassUserDto(User user);

    @Mapping(target = "role.authority", source = "role")
    User noPassUserDtoToUser(NoPassUserDto dto);

    List<NoPassUserDto> userListToNoPassUserList(List<User> users);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    User registerUserDtoToUser(RegisterUserDto dto);

    @Mapping(source = "username", target = "email")
    FullUserDto userToFullUserDto(User user);
}
