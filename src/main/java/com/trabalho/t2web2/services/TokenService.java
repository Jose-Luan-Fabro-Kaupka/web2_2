package com.trabalho.t2web2.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.trabalho.t2web2.models.UsuarioModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(UsuarioModel usuario) {
        return JWT.create()
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now().plusMinutes(50).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("VivaCristoRei"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("VivaCristoRei"))
                .build().verify(token)
                .getSubject();
    }
}
