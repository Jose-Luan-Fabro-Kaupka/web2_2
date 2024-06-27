package com.trabalho.t2web2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "atividades")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AtividadeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artefato_id")
    @JsonManagedReference
    private ArtefatoModel artefatoModel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonManagedReference
    private UsuarioModel usuarioModel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArtefatoModel getArtefatoModel() {
        return artefatoModel;
    }

    public void setArtefatoModel(ArtefatoModel artefatoModel) {
        this.artefatoModel = artefatoModel;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }
}
