package com.arpansharma.expense_tracker_api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final long JWT_EXPIRATION_DURATION = 5*60*60;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails){

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION_DURATION * 1000))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public String getUserNameFromToken(String token){
        return getClaimsFromToken(token,Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public Boolean validateToken(String jwtToken, UserDetails userDetails) {

        String username = getUserNameFromToken(jwtToken);

        return (username.equals(userDetails.getUsername()) && !tokenIsExpired(jwtToken));

    }

    private boolean tokenIsExpired(String jwtToken) {
        final Date expiryDate= getExpirationDateFromToken(jwtToken);

        return expiryDate.before(new Date(System.currentTimeMillis()));
    }
}
