package br.com.sifat.configs.security;

import br.com.sifat.core.dtos.JwtTokenDecodeDTO;
import br.com.sifat.core.interfaces.JwtProviderUtils;
import br.com.sifat.core.utils.Json;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;



@Log4j2
@Component
public class JwtProvider implements JwtProviderUtils {

    @Value("${sifat.auth.jwtSecret}")
    protected String jwtSecret;



    @NotNull
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public JwtTokenDecodeDTO getPayload(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token).getBody();

        return Json.convertValue(claims, JwtTokenDecodeDTO.class);
    }

    public boolean validateJwt(String authToken) {
        try {
            SecretKey secret = getSecretKey();
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(authToken);

            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is impety: {}", e.getMessage());
        }

        return false;
    }


    public String getClaimNameJwt(String token, String claimName) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody().get(claimName).toString();
    }


}
