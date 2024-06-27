package com.trabalho.t2web2.repositories;

import com.trabalho.t2web2.models.ArtefatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtefatoRepository extends JpaRepository<ArtefatoModel, Integer> {
}
