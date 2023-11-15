package com.leoric.eshop.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.leoric.eshop.models.LocalUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class JWTService {
    @Value("${JWT_ALGORITHM_KEY}")
    private String algorithmKey;
    @Value("${JWT_ISSUER}")
    private String issuer;
    @Value("${JWT_EXPIRY_IN_SECONDS}")
    private int expiryInSeconds;
    private Algorithm algorithm;
    private static final String USERNAME_KEY = "USERNAME";
    private static final String ROLES = "ROLES";

    @PostConstruct
    public void postConstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }
    public String generateJWT(LocalUser user){
        List<String> roles = Arrays.asList(user.getRoles().split("\\s+"));
        return JWT.create()
                .withClaim(USERNAME_KEY, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*expiryInSeconds))
                .withIssuer(issuer)
                .withClaim(ROLES, roles)
                .sign(algorithm);
    }
    public String getUsername(String token){
        return JWT.decode(token).getClaim(USERNAME_KEY).asString();
    }
    public List<String> getRoles(String token){
        return JWT.decode(token).getClaim(ROLES).asList(String.class);
    }
}
