package by.itechart.service.refreshToken;

import by.itechart.model.exception.TokenRefreshException;
import by.itechart.model.refreshToken.RefreshToken;
import by.itechart.model.user.User;
import by.itechart.repository.RefreshTokenRepository;
import by.itechart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static by.itechart.model.util.ValidationUtil.validateOptional;
import static by.itechart.model.util.ValidationUtil.validateParams;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refreshExpirationMs}")
    private long refreshExpirationMs;

    private final RefreshTokenRepository tokenRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public RefreshToken createRefreshToken(String username) throws Throwable {

        validateParams(username);

        log.info("Create new refresh token for user with the name = {}",
                                                                        username);
        RefreshToken refreshToken = new RefreshToken();

        Optional<User> possibleUser = userRepository.getUserByUsername(username);
        User user = validateOptional(possibleUser, User.class);

        refreshToken.setSubject(user.getUsername());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshExpirationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        RefreshToken storedToken = tokenRepository.save(refreshToken);

        return storedToken;
    }

    @Override
    public RefreshToken findByToken(String token) throws Throwable {

        validateParams(token);

        log.info("Receive a refresh token by the token");

        Optional<RefreshToken> possibleToken = tokenRepository.findByToken(token);
        RefreshToken validToken = validateOptional(possibleToken, RefreshToken.class);

        return validToken;
    }

    @Override
    @Transactional
    public RefreshToken verifyExpiration(RefreshToken refreshToken) throws Throwable {

        validateParams(refreshToken);
        
        log.info("Verify a refresh token for expiration");

        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {

            tokenRepository.delete(refreshToken);

            throw new TokenRefreshException("Refresh token was expired. Please make a new sign in request");
        }

        return refreshToken;
    }

    @Override
    @Transactional
    public void deleteRefreshTokenById(Long tokenId) {

        validateParams(tokenId);

        log.info("Delete the refresh token by its ID = {}",
                                                          tokenId);

        tokenRepository.deleteById(tokenId);
    }

    @Override
    @Transactional
    public void deleteRefreshTokenByUsername(String username) {

        validateParams(username);

        log.info("Delete the refresh token by the username = {}",
                                                                 username);

        tokenRepository.deleteBySubject(username);
    }
}
