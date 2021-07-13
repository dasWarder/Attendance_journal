package by.itechart.mapping.token;


import by.itechart.mapping.dto.token.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    @Mapping(target = "refreshToken", ignore = true)
    @Mapping(source = "authToken", target = "authToken")
    Token fromStringToToken(String authToken);

    @Mapping(source = "authToken", target = "authToken")
    @Mapping(source = "refreshToken", target = "refreshToken")
    Token fromStringsToToken(String authToken, String refreshToken);
}
