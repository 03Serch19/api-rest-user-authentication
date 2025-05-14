package com.uso2spring.springproyectaprender.utils;

import io.jsonwebtoken.Claims;
 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.security.Keys;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.stereotype.Component;

 import java.security.Key;
 import java.util.Base64;
 import java.util.Date;
 
 import javax.crypto.SecretKey;

 /**
  * @author Mahesh
  */
 @Component
 public class JWTUtil {
     @Value("${security.jwt.secret}")
     private String key;

     @Value("${security.jwt.issuer}")
     private String issuer;

     @Value("${security.jwt.ttlMillis}")
     private long ttlMillis;

     private final Logger log = LoggerFactory
             .getLogger(JWTUtil.class);

     /**
      * Create a new token.
      *
      * @param id
      * @param subject
      * @return
      */
     public String create(String id, String subject) {
         // The JWT signature algorithm used to sign the token

         long nowMillis = System.currentTimeMillis();
         Date now = new Date(nowMillis);

         // Sign JWT with our ApiKey secret
         byte[] apiKeySecretBytes = Base64.getDecoder().decode(key);
         //Key signingKey = Keys.hmacShaKeyFor(apiKeySecretBytes);
         SecretKey signingKey = Keys.hmacShaKeyFor(apiKeySecretBytes);
         // Set the JWT Claims
         io.jsonwebtoken.JwtBuilder builder = Jwts.builder()
                 .id(id)
                 .issuedAt(now)
                 .subject(subject)
                 .issuer(issuer)
                 .signWith(signingKey);

         if (ttlMillis >= 0) {
             long expMillis = nowMillis + ttlMillis;
             Date exp = new Date(expMillis);
             builder.expiration(exp);
         }

         // Builds the JWT and serializes it to a compact, URL-safe string
         return builder.compact();
     }

     /**
      * Method to validate and read the JWT
      *
      * @param jwt
      * @return
      */
     public String getValue(String jwt) {
         // This line will throw an exception if it is not a signed JWS (as
         // expected)
         byte[] apiKeySecretBytes = Base64.getDecoder().decode(key);
         SecretKey signingKey = Keys.hmacShaKeyFor(apiKeySecretBytes);
         Claims claims = Jwts.parser()
                 .verifyWith(signingKey)
                 .build()
                 .parseSignedClaims(jwt)
                 .getPayload();

         return claims.getSubject();
     }

     /**
      * Method to validate and read the JWT
      *
      * @param jwt
      * @return
      */
     public String getKey(String jwt) {
         // This line will throw an exception if it is not a signed JWS (as
         // expected)
         byte[] apiKeySecretBytes = Base64.getDecoder().decode(key);
         SecretKey signingKey = Keys.hmacShaKeyFor(apiKeySecretBytes);
         Claims claims = Jwts.parser()
         .verifyWith(signingKey)
         .build()
         .parseSignedClaims(jwt)
         .getPayload();

         return claims.getId();
     }
 }