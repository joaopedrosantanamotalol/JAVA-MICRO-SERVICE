package com.usuario.usuario.base.persistence.repository;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.usuario.usuario.base.application.ports.TokenService;
import com.usuario.usuario.base.domain.entities.UsuarioEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements TokenService {

    private final String SECRET = "JAMUDOJAMUDOJAMUDOJAMUDOJAMUDO";

    @Override
    public String generateAccessToken(UsuarioEntity usuario) {

        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("type", "acess")
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 15 * 60 * 1000)
                )
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    @Override
    public String generateRefreshToken(UsuarioEntity usuario) {

        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("type", "refresh")
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)
                )
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    @Override
    public boolean validateRefreshToken(String token) {

        try {

            var claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return "refresh".equals(claims.get("type"));

        } catch (Exception e) {

            return false;
        }
    }
    @Override
    public String getEmailFromToken(String token) {

        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
