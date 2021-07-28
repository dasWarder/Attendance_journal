package by.itechart.repository;

import by.itechart.model.refreshToken.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteBySubject(String username);
}
