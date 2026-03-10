package com.biraj.ecomerceappapi.util;


import com.biraj.ecomerceappapi.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JWTUtil {

    private  String secretKey="birajroythisisaverysecuresecretkeyforjwt123456";

    public SecretKey getSecretKey(){

        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    public  String generateToken(User user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("userId",user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()  + 1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }
    public  String extractEmail(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        }

    public Long extractUserId(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("userId", Long.class);
    }

}
