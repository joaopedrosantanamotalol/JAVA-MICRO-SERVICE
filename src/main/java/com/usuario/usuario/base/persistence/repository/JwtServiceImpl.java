package com.usuario.usuario.base.persistence.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.usuario.usuario.base.application.ports.TokenService;
import com.usuario.usuario.base.domain.entities.UsuarioEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class JwtServiceImpl implements TokenService {

    private final String SECRET;

    private final JdbcTemplate jdbcTemplate;

    public JwtServiceImpl(
            @Value("${jwt.secret}") String secret,
            JdbcTemplate jdbcTemplate
    ) {
        this.SECRET = secret;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String generateAccessToken(UsuarioEntity usuario) {

        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("type", "access")
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

            return "refresh".equals(claims.get("type"))
        && !isTokenRevoked(token);

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

    @Override
    public boolean validateAccessToken(String token) {
        try {
            var claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(
                            SECRET.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            boolean isAccessToken = "access".equals(claims.get("type"));

            if (!isAccessToken) {
                System.err.println("[JWT] Token recebido não é do tipo access.");
                return false;
            }

            boolean revoked = isTokenRevoked(token);

            if (revoked) {
                System.err.println("[JWT] Access token revogado.");
                return false;
            }

            return true;

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.err.println("[JWT] Access token expirado.");
            return false;

        } catch (io.jsonwebtoken.security.SignatureException e) {
            System.err.println("[JWT] Assinatura inválida.");
            return false;

        } catch (Exception e) {
            System.err.println(
                    "[JWT] Falha na validação: "
                    + e.getClass().getSimpleName()
                    + " - "
                    + e.getMessage()
            );
            return false;
        }
    }

    @Override
public void revokeToken(String token) {
    if (token == null || token.isBlank()) {
        return;
    }

    var claims = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
            .build()
            .parseSignedClaims(token)
            .getPayload();

    Date expiration = claims.getExpiration();

    if (expiration == null || !expiration.after(new Date())) {
        return;
    }

    String hash = hashToken(token);

    jdbcTemplate.update("""
        INSERT INTO tokens_revogados (token_hash, expira_em)
        VALUES (?, ?)
        ON DUPLICATE KEY UPDATE expira_em = VALUES(expira_em)
        """,
        hash,
        new Timestamp(expiration.getTime())
    );
}

    @Override
    public boolean isTokenRevoked(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }

        String hash = hashToken(token);

        Integer count = jdbcTemplate.queryForObject("""
            SELECT COUNT(*)
            FROM tokens_revogados
            WHERE token_hash = ?
            AND expira_em > CURRENT_TIMESTAMP
            """,
            Integer.class,
            hash
        );

        return count != null && count > 0;
    }

    private String hashToken(String token) {
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256")
                    .digest(token.getBytes(StandardCharsets.UTF_8));

            return java.util.HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 indisponível", e);
        }
    }

}
