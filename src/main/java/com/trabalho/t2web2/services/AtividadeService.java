package com.trabalho.t2web2.services;

import com.trabalho.t2web2.dtos.AtividadeRecordDTO;
import com.trabalho.t2web2.models.ArtefatoModel;
import com.trabalho.t2web2.models.AtividadeModel;
import com.trabalho.t2web2.models.UsuarioModel;
import com.trabalho.t2web2.repositories.ArtefatoRepository;
import com.trabalho.t2web2.repositories.AtividadeRepository;
import com.trabalho.t2web2.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ArtefatoRepository artefatoRepository;


    public AtividadeModel registrarAtividade(AtividadeRecordDTO atividadeRecordDTO){
        AtividadeModel atividadeModel = new AtividadeModel();

        Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(atividadeRecordDTO.usuario_id());
        Optional<ArtefatoModel> artefatoModel = artefatoRepository.findById(atividadeRecordDTO.artefato_id());

        usuarioModel.ifPresent(atividadeModel::setUsuarioModel);

        artefatoModel.ifPresent(atividadeModel::setArtefatoModel);

        atividadeModel.setTitulo(atividadeRecordDTO.titulo());
        atividadeModel.setDescricao(atividadeRecordDTO.descricao());

        atividadeRepository.save(atividadeModel);
        return atividadeModel;
    }

    public List<AtividadeModel> consultarTodasAtividades(){ return atividadeRepository.findAll(); }

    public Object consultarAtividadePorId(int id){
        try {
            return atividadeRepository.findByIdWithUsuarioEArtefato(id);
        } catch (Exception e){
            return atividadeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
        }
    }

    public AtividadeModel editarAtividade(int id, AtividadeRecordDTO atividadeRecordDTO){
        AtividadeModel atividadeModel = atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(atividadeRecordDTO.usuario_id());
        Optional<ArtefatoModel> artefatoModel = artefatoRepository.findById(atividadeRecordDTO.artefato_id());

        atividadeModel.setUsuarioModel(usuarioModel.get());
        atividadeModel.setArtefatoModel(artefatoModel.get());

        atividadeModel.setTitulo(atividadeRecordDTO.titulo());
        atividadeModel.setDescricao(atividadeRecordDTO.descricao());

        return atividadeRepository.save(atividadeModel);
    }

    public String excluirAtividade(int id){
        AtividadeModel atividadeModel = atividadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        atividadeRepository.delete(atividadeModel);
        return "Atividade excluida com sucesso";
    }

}
