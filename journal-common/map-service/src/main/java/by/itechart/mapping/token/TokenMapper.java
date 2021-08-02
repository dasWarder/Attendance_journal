package by.itechart.mapping.token;


import by.itechart.mapping.dto.token.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TokenMapper {

    @Mapping(target = "refreshToken", ignore = true)
    @Mapping(source = "authToken", target = "authToken")
    Token fromStringToToken(String authToken);

    @Mapping(target = "authToken", source = "authToken")
    @Mapping(target = "refreshToken", source = "refreshToken")
    Token fromStringsToToken(String authToken, String refreshToken);

}
