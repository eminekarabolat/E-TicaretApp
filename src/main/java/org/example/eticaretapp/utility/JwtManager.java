package org.example.eticaretapp.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class JwtManager {

    @Value("${ETICARET_SECRET_KEY}")
    private String SecretKey;
    @Value("${ETICARET_ISSUER}")
    private String Issuer;

    private final Long ExDate = 1000L * 60 * 10; //10 dk sonra iptal olsun.

    public String createToken(Long authId) {
        Date createdDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + ExDate);

        Algorithm algorithm = Algorithm.HMAC512(SecretKey);

        String token = JWT.create()
                .withAudience() //-> token kim için üretiliyor.
                .withIssuer(Issuer) //-> hangi uygulama tarafından oluşturuluyor
                .withIssuedAt(createdDate) //-> token oluşturulma tarihi
                .withExpiresAt(expirationDate) //token ne kadar süre geçerli olacak
                .withClaim("authId", authId)
                .withClaim("key", "ETicaret_App")
                .sign(algorithm);
        return token;
    }

    public Optional<Long> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(SecretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (Objects.isNull(decodedJWT))
                return Optional.empty();
            Long authId = decodedJWT.getClaim("authId").asLong();
            return Optional.of(authId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}