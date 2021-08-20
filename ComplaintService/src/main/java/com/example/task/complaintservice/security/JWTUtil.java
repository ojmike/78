package com.example.task.complaintservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String SECRET;


    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token).getBody();
//            Jwts.builder().
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");


        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            System.out.println(headerAuth.substring(7));
            return headerAuth.substring(7);
        }
        return null;
    }


}