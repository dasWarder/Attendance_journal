package by.itechart.service.authority;

import by.itechart.model.user.UserAuthority;
import by.itechart.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.itechart.model.util.ValidationUtil.validateOptional;
import static by.itechart.model.util.ValidationUtil.validateParams;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public UserAuthority saveAuthority(UserAuthority authority) {

        validateParams(authority);

        log.info("Store an authority with a name = {}",
                                                      authority.getAuthority());
        UserAuthority storedAuthority = authorityRepository.save(authority);

        return storedAuthority;
    }

    @Override
    @Transactional(readOnly = true)
    public UserAuthority getAuthorityById(Long authorityId) throws Throwable {

        validateParams(authorityId);

        log.info("Receive an authority by ID = {}",
                                                   authorityId);
        Optional<UserAuthority> possibleAuthority = authorityRepository.findById(authorityId);
        UserAuthority validAuthority = validateOptional(possibleAuthority, UserAuthority.class);

        return validAuthority;
    }

    @Override
    @Transactional(readOnly = true)
    public UserAuthority getAuthorityByName(String name) throws Throwable {

        validateParams(name);

        log.info("Receive an authority by the name = {}",
                                                        name);
        Optional<UserAuthority> possibleAuthority = authorityRepository.getUserAuthorityByAuthority(name);
        UserAuthority validAuthority = validateOptional(possibleAuthority, UserAuthority.class);

        return validAuthority;
    }

    @Override
    @Transactional
    public void deleteAuthorityById(Long authorityId) {

        validateParams(authorityId);

        log.info("Remove an authority by the ID = {}",
                                                      authorityId);
        authorityRepository.deleteById(authorityId);
    }

    @Override
    @Transactional
    public void deleteAuthorityByName(String name) {

        validateParams(name);

        log.info("Remove an authority by the name = {}",
                                                        name);
        authorityRepository.deleteUserAuthorityByAuthority(name);
    }

    @Override
    @Transactional
    public UserAuthority updateAuthority(Long authorityId, UserAuthority updateAuthority) throws Throwable {

        validateParams(authorityId, updateAuthority);

        log.info("Update an authority with the ID = {}",
                                                        authorityId);
        UserAuthority authorityById = this.getAuthorityById(authorityId);
        updateAuthority.setId(authorityById.getId());
        UserAuthority updatedAuthority = authorityRepository.save(updateAuthority);

        return updatedAuthority;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserAuthority> getAllAuthorities() {

        log.info("Receive a list of all authorities");

        List authorities = (List) authorityRepository.findAll();

        return authorities;
    }
}
