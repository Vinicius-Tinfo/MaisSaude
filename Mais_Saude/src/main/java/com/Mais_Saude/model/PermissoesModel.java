package com.Mais_Saude.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="permissoes", schema = "mais_saude")
public class PermissoesModel {

@Id
@Column(name ="id")
private Long id;

@Column(name ="descricao")
private String descricao;


@ManyToMany(mappedBy = "permissoes")
private List<UsuarioModel> usuarios = new ArrayList<>();

public List<UsuarioModel> getUsuarios() {
    return usuarios;
}
public void setUsuarios(List<UsuarioModel> usuarios) {
    this.usuarios = usuarios;
}
public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}

public String getDescricao() {
    return descricao;
}

public void setDescricao(String descricao) {
    this.descricao = descricao;
}
	
}
