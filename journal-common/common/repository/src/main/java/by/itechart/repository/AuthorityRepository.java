package by.itechart.repository;

import by.itechart.model.user.UserAuthority;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<UserAuthority, Long> {

    Optional<UserAuthority> getUserAuthorityByAuthority(String authorityName);

    void deleteUserAuthorityByAuthority(String authorityName);
}
