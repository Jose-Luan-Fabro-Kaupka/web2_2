package com.trabalho.t2web2.services;

import com.trabalho.t2web2.dtos.UsuarioRecordDTO;
import com.trabalho.t2web2.models.UsuarioModel;
import com.trabalho.t2web2.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioModel registrarUsuario(UsuarioRecordDTO usuarioRecordDTO){
        UsuarioModel usuarioExiste = repository.findByUsername(usuarioRecordDTO.username());

        if (usuarioExiste != null){
            throw new RuntimeException("Ja existe um usuario com esse nome!");
        }

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUsername(usuarioRecordDTO.username());
        usuarioModel.setPass(passwordEncoder.encode(usuarioRecordDTO.pass()));

        repository.save(usuarioModel);
        return usuarioModel;
    }

    public List<UsuarioModel> consultarTodosUsuarios(){ return repository.findAll(); }

    public Object consultarUsuarioPorId(int id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UsuarioModel editarUsuario(int id, UsuarioRecordDTO usuarioRecordDTO){
        UsuarioModel usuarioModel = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        usuarioModel.setUsername(usuarioRecordDTO.username());
        if (!usuarioRecordDTO.pass().isEmpty()){
            usuarioModel.setPass(usuarioRecordDTO.pass());
        }
        return repository.save(usuarioModel);
    }

    public String excluirUsuario(int id){
        UsuarioModel usuarioModel = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        repository.delete(usuarioModel);
        return "Usuário excluído com sucesso";
    }
}
