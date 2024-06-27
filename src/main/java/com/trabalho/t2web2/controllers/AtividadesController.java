package com.trabalho.t2web2.controllers;

import com.trabalho.t2web2.dtos.AtividadeRecordDTO;
import com.trabalho.t2web2.services.AtividadeService;
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
public class AtividadesController {
    @Autowired
    AtividadeService service;

    @Operation(description = "Insere uma atividade no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atividade criada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar atividade")
    })
    @PostMapping(value = "/atividades", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrarAtividade(@RequestBody @Validated AtividadeRecordDTO atividadeRecordDTO){
        var atividade = service.registrarAtividade(atividadeRecordDTO);
        return ResponseEntity.status(HttpStatus.OK).body(atividade);
    }

    @Operation(description = "Consulta todas as atividades existentes no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
    })
    @GetMapping(value = "/atividades", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarAtividades(){
        return ResponseEntity.status(HttpStatus.OK).body(service.consultarTodasAtividades());
    }

    @Operation(description = "Consulta uma determinada atividade no banco de dados pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "404", description = "Atividade não encontrada")
    })
    @GetMapping(value = "/atividades/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarAtividadePorId(@PathVariable(value = "id") int id){
        try {
            var atividade = service.consultarAtividadePorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(atividade);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atividade nao encontrada");
        }
    }

    @Operation(description = "Edita uma atividade no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atividade editada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "404", description = "Atividade não encontrada")
    })
    @PutMapping(value = "/atividades/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> alterarAtividade(@PathVariable(value = "id") int id, @RequestBody @Validated AtividadeRecordDTO atividadeRecordDTO){
        try {
            var atividadeAtualizada = service.editarAtividade(id, atividadeRecordDTO);
            return ResponseEntity.status(HttpStatus.OK).body(atividadeAtualizada);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atividade nao encontrada");
        }
    }

    @Operation(description = "Exclui uma atividade do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atividade excluida com sucesso"),
            @ApiResponse(responseCode = "403", description = "Rejeitado pelo servidor"),
            @ApiResponse(responseCode = "404", description = "Atividade não encontrada")
    })
    @DeleteMapping(value = "/atividades/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> excluirAtividade(@PathVariable(value = "id") int id){
        try {
            service.excluirAtividade(id);
            return ResponseEntity.status(HttpStatus.OK).body("Atividade excluida com sucesso");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atividade nao encontrada");
        }
    }
}
