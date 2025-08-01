package com.Mais_Saude.model;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="consulta", schema = "mais_saude")
public class ConsultaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "data")
	private LocalDateTime data;

	@Column(name = "id_usuario")
	private String id_usuario;
	

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente", nullable = false)
	private PacientesModel paciente;
	



	public PacientesModel getPaciente() {
		return paciente;
	}

	public void setPaciente(PacientesModel paciente) {
		this.paciente = paciente;
	}

	@Column(name = "sintomas")
	private String sintomas;

	@Column(name = "prontuario")
	private String prontuario;

	@Column(name = "nome_medico")
	private String nome_medico;

	@Column(name = "cpf_paciente")
	private String cpf_paciente;

	public String getCpf_paciente() {
		return cpf_paciente;
	}

	public void setCpf_paciente(String cpf_paciente) {
		this.cpf_paciente = cpf_paciente;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome_medico() {
		return nome_medico;
	}

	public void setNome_medico(String nome_medico) {
		this.nome_medico = nome_medico;
	}


	public void setId(Long id) {
		this.id = id;
	}

	

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	



	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getProntuario() {
		return prontuario;
	}

	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}
}
