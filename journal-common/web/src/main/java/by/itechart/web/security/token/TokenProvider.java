package by.itechart.web.security.token;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long jwtExpiration;

    public String generateToken(String login) {
        Date date =  new Date((new Date()).getTime() + jwtExpiration);

        log.info("The Token is valid until {}", date);
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {

        try {

            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;

        } catch (Exception e) {

            return false;
        }
    }
}
