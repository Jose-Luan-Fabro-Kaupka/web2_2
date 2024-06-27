package com.trabalho.t2web2.repositories;

import com.trabalho.t2web2.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    UsuarioModel findByUsername(String username);

}
