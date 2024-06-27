package com.trabalho.t2web2.controllers;

import com.trabalho.t2web2.dtos.Login;
import com.trabalho.t2web2.models.UsuarioModel;
import com.trabalho.t2web2.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Tag(name = "web2t2")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Operation(description = "Realiza o login do usuário")@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "500", description = "Erro ao efetuar login")
    })
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> efetuarLogin(@RequestBody Login login){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.username(), login.pass());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var usuario = (UsuarioModel) authenticate.getPrincipal();

        return ResponseEntity.status(HttpStatus.OK).body(tokenService.gerarToken(usuario));
    }
}
