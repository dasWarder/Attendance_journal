package by.itechart.service.authority;

import by.itechart.model.user.UserAuthority;

import java.util.List;

public interface AuthorityService {

    UserAuthority saveAuthority(UserAuthority authority);

    UserAuthority getAuthorityById(Long authorityId) throws Throwable;

    UserAuthority getAuthorityByName(String name) throws Throwable;

    void deleteAuthorityById(Long authorityId);

    void deleteAuthorityByName(String name);

    UserAuthority updateAuthority(Long authorityId, UserAuthority updateAuthority) throws Throwable;

    List<UserAuthority> getAllAuthorities();
}
