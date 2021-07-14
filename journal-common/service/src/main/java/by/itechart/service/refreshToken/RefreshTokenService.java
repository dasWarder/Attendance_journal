package by.itechart.service.refreshToken;

import by.itechart.model.exception.TokenRefreshException;
import by.itechart.model.refreshToken.RefreshToken;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(String username) throws Throwable;

    RefreshToken findByToken(String token) throws Throwable;

    RefreshToken verifyExpiration(RefreshToken refreshToken) throws TokenRefreshException;

    void deleteRefreshTokenById(Long tokenId);

    void deleteRefreshTokenByUsername(String username);
}
