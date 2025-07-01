package com.Mais_Saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Mais_Saude.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

boolean existsByCpf(String cpf);
boolean existsByTelefone(String telefone);
    
}
