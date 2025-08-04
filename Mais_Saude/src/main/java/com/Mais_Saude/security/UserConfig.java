package com.Mais_Saude.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.Mais_Saude.model.UsuarioModel;
import com.Mais_Saude.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserConfig implements UserDetailsService {

@Autowired
private UsuarioRepository usuarioRepository;

@Override
public UserDetails loadUserByUsername ( String cpf) throws UsernameNotFoundException{
    UsuarioModel usuario = usuarioRepository.findByCpf(cpf);

    if ( usuario == null){
     throw new UsernameNotFoundException("Usuário não encontrado");};




    return new User(
            usuario.getCpf(),
            usuario.getPassword(),
            true,   // accountNonExpired
            true,   // credentialsNonExpired
            true,   // accountNonLocked
            true,   // enabled
            usuario.getAuthorities()
    );
    
}
}