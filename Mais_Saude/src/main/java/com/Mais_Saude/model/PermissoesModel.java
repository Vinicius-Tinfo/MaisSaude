package com.Mais_Saude.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="permissoes", schema = "mais_saude")
public class PermissoesModel {

@Id
@Column(name ="id")
private Long id;

@Column(name ="descricao")
private String descricao;


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
