package com.trabalho.t2web2.repositories;


import com.trabalho.t2web2.models.AtividadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeModel, Integer> {
    @Query("SELECT at FROM AtividadeModel at " +
            "INNER JOIN FETCH at.artefatoModel art " +
            "INNER JOIN FETCH at.usuarioModel usr " +
            "WHERE at.id = :id")
    Optional<AtividadeModel> findByIdWithUsuarioEArtefato(@Param("id") int id);
}
