package by.itechart.mapping.authority;

import by.itechart.mapping.dto.authority.AuthorityDto;
import by.itechart.model.user.UserAuthority;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    AuthorityMapper INSTANCE = Mappers.getMapper( AuthorityMapper.class );

    AuthorityDto userAuthorityToAuthorityDto(UserAuthority authority);

    UserAuthority authorityDtoToUserAuthority(AuthorityDto authorityDto);
}
