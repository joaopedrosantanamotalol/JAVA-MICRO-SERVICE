package com.comercio.comercio.base.infra.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TokenRevocationService {

    private final JdbcTemplate jdbcTemplate;

    public TokenRevocationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isRevoked(String token) {
        if (token == null || token.isBlank()) {
            return true;
        }

        try {
            String hash = hashToken(token);

            Integer count = jdbcTemplate.queryForObject("""
                SELECT COUNT(*)
                FROM tokens_revogados
                WHERE token_hash = ?
                """, Integer.class, hash);

            boolean revoked = count == null || count > 0;

            if (revoked) {
                System.err.println("[JWT] Token revogado.");
            }

            return revoked;

        } catch (DataAccessException exception) {
            System.err.println(
                "[JWT] Não foi possível consultar revogações. "
                + "Requisição não será autenticada."
            );
            return true;
        }
    }

    private String hashToken(String token) {
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256")
                    .digest(token.getBytes(StandardCharsets.UTF_8));

            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException(
                "SHA-256 indisponível", exception);
        }
    }
}
