package by.itechart.mapping.user;


import by.itechart.mapping.dto.user.UserDto;
import by.itechart.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "email")
    UserDto userToUserDto(User user);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    User userDtoToUser(UserDto userDto);

    List<UserDto> userListToUserDtoList(List<User> users);

}
