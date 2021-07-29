package by.itechart.mapping.authority;

import by.itechart.mapping.dto.authority.AuthorityDto;
import by.itechart.model.user.UserAuthority;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AuthorityMapper {

    @Mapping(source = "authority", target = "name")
    AuthorityDto userAuthorityToAuthorityDto(UserAuthority authority);

    @InheritInverseConfiguration
    UserAuthority authorityDtoToUserAuthority(AuthorityDto authorityDto);

    List<AuthorityDto> userAuthorityListToAuthorityDtoList(List<UserAuthority> authorities);
}
