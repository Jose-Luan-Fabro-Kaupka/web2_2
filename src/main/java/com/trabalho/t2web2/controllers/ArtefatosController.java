package com.trabalho.t2web2.controllers;

import com.trabalho.t2web2.dtos.ArtefatoRecordDTO;
import com.trabalho.t2web2.services.ArtefatoService;
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
public class ArtefatosController {
    @Autowired
    ArtefatoService service;

    @Operation(description = "Insere um artefato no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artefato criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar artefato")
    })
    @PostMapping(value = "/artefatos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrarArtefato(@RequestBody @Validated ArtefatoRecordDTO artefatoRecordDTO){
        var artefato = service.registrarArtefato(artefatoRecordDTO);
        return ResponseEntity.status(HttpStatus.OK).body(artefato);
    }

    @Operation(description = "Consulta todos os artefatos existentes no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
    })
    @GetMapping(value = "/artefatos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarArtefatos(){
        return ResponseEntity.status(HttpStatus.OK).body(service.consultarTodosArtefatos());
    }

    @Operation(description = "Consulta um determinado artefato no banco de dados pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "404", description = "Artefato não encontrado")
    })
    @GetMapping(value = "/artefatos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarArtefatoPorId(@PathVariable(value = "id") int id){
        try {
            var artefato = service.consultarArtefatoPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(artefato);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artefato nao encontrado");
        }
    }

    @Operation(description = "Edita um artefato no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artefato editado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "404", description = "Artefato não encontrado")
    })
    @PutMapping(value = "/artefatos/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> alterarArtefato(@PathVariable(value = "id") int id, @RequestBody @Validated ArtefatoRecordDTO artefatoRecordDTO){
        try {
            var artefato = service.editarArtefato(id, artefatoRecordDTO);
            return ResponseEntity.status(HttpStatus.OK).body(artefato);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artefato nao encontrado");
        }
    }

    @Operation(description = "Exclui um artefato do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Artefato excluido com sucesso"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "404", description = "Artefato não encontrado")
    })
    @DeleteMapping(value = "/artefatos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> excluirArtefato(@PathVariable(value = "id") int id){
        try {
            service.excluirArtefato(id);
            return ResponseEntity.status(HttpStatus.OK).body("Artefato excluido com sucesso");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artefato nao encontrado");
        }
    }

}
