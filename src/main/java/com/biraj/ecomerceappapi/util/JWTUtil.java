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
    @Value("{jwt_secret}")
    private  String secretKey;

    public SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    public  String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId",user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()  + 1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }

}
