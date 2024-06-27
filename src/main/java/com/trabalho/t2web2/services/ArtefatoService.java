package com.trabalho.t2web2.services;

import com.trabalho.t2web2.dtos.ArtefatoRecordDTO;
import com.trabalho.t2web2.models.ArtefatoModel;
import com.trabalho.t2web2.repositories.ArtefatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtefatoService {

    @Autowired
    ArtefatoRepository repository;
    public ArtefatoModel registrarArtefato(ArtefatoRecordDTO artefatoRecordDTO){
        ArtefatoModel artefatoModel = new ArtefatoModel();
        BeanUtils.copyProperties(artefatoRecordDTO, artefatoModel);

        repository.save(artefatoModel);
        return artefatoModel;
    }

    public List<ArtefatoModel> consultarTodosArtefatos(){ return repository.findAll(); }

    public ArtefatoModel consultarArtefatoPorId(int id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artefato não encontrado"));
    }

    public ArtefatoModel editarArtefato(int id, ArtefatoRecordDTO artefatoRecordDTO){
        ArtefatoModel artefatoModel = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artefato não encontrado"));

        BeanUtils.copyProperties(artefatoRecordDTO, artefatoModel);
        return repository.save(artefatoModel);
    }

    public String excluirArtefato(int id){
        ArtefatoModel artefato = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artefato não encontrado"));

        repository.delete(artefato);
        return "Artefato excluído com sucesso";
    }
}
