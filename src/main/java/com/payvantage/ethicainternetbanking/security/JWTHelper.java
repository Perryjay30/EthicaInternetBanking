/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payvantage.ethicainternetbanking.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


@Component
public class JWTHelper {

    private static final Logger LOG = LoggerFactory.getLogger(JWTHelper.class.getName());

    private final Environment environment;

    public JWTHelper(Environment environment) {
        this.environment = environment;
    }

    
    public String createShortLiveToken(String userId, String phoneNumber, int validityInMinutes) {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, validityInMinutes);
        Date expiryDate = cal.getTime();
        try {
            Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(environment.getProperty("jwt.secret.key")));
            return JWT.create()
                    .withIssuer(environment.getProperty("jwt.issuer"))
                    .withExpiresAt(expiryDate)
                    .withIssuedAt(Calendar.getInstance().getTime())
                    .withNotBefore(Calendar.getInstance().getTime())
                    .withClaim("userId", userId)
                    .withClaim("type", "sl")
                    .withClaim("phoneNumber", phoneNumber)
                    .withJWTId(userId)
                    .sign(algorithm);
        } catch (JWTCreationException | IllegalArgumentException exception) {
            LOG.info(exception.getMessage());
        }
        return null;
    }
    

   

    public DecodedJWT validateToken(String token) {
        try {
            token=token.replace("Bearer", "").trim();
            Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(environment.getProperty("jwt.secret.key")));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(environment.getProperty("jwt.issuer"))
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (JWTVerificationException | IllegalArgumentException exception) {
            LOG.info(exception.getMessage());
        }
        return null;
    }

    public String getUserId(String token) {
        try {
            token=token.replace("Bearer", "").trim();
            Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(environment.getProperty("jwt.secret.key")));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(environment.getProperty("jwt.issuer"))
                    .build();
            DecodedJWT jwt = verifier.verify(token);
           return jwt.getId();
        } catch (Exception exception) {
            LOG.info(exception.getMessage());
        }
        return null;
    }
    
    public String getClaim(String token,String claim) {
        try {
            token=token.replace("Bearer", "").trim();
            Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(environment.getProperty("jwt.secret.key")));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(environment.getProperty("jwt.issuer"))
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTVerificationException | IllegalArgumentException exception) {
            LOG.info(exception.getMessage());
        }
        return null;
    }

    private Key getSignKey() {
        byte [] keyBytes = Decoders.BASE64URL.decode(environment.getProperty("jwt.secret.key"));
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
