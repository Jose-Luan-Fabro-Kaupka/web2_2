package com.trabalho.t2web2.controllers;

import com.trabalho.t2web2.dtos.UsuarioRecordDTO;
import com.trabalho.t2web2.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Tag(name = "web2t2")
public class UsuariosController {
    @Autowired
    UsuarioService service;

    @Operation(description = "Insere um usuario no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "409", description = "Usuário já existe"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar usuário")
    })
    @PostMapping(value = "/usuarios/registrar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrarUsuario(@RequestBody @Validated UsuarioRecordDTO usuarioRecordDTO){
        try{
            var usuario = service.registrarUsuario(usuarioRecordDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(description = "Consulta todos os usuarios existentes no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
    })
    @GetMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(service.consultarTodosUsuarios());
    }

    @Operation(description = "Consulta um determinado usuario no banco de dados pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/usuarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarUsuarioPorId(@PathVariable(value = "id") int id){
        try {
            var usuario = service.consultarUsuarioPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
    }

    @Operation(description = "Edita um usuario no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário editado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping(value = "/usuarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> alterarUsuario(@PathVariable(value = "id") int id, @RequestBody @Validated UsuarioRecordDTO usuarioRecordDTO){
        try {
            var usuarioAtualizado = service.editarUsuario(id, usuarioRecordDTO);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
    }

    @Operation(description = "Exclui um usuario do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluido com sucesso"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping(value = "/usuarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> excluirUsuario(@PathVariable(value = "id") int id){
        try {
            service.excluirUsuario(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario excluido com sucesso");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
    }
}
