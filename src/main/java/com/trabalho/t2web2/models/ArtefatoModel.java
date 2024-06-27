package com.trabalho.t2web2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "artefatos")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ArtefatoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @OneToOne(mappedBy = "artefatoModel", fetch = FetchType.LAZY)
    @JsonBackReference
    private AtividadeModel atividadeModel;

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public AtividadeModel getAtividadeModel() {
        return atividadeModel;
    }

    public void setAtividadeModel(AtividadeModel atividadeModel) {
        this.atividadeModel = atividadeModel;
    }
}
