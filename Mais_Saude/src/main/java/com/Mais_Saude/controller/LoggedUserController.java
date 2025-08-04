package com.Mais_Saude.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Mais_Saude.model.UsuarioModel;
import com.Mais_Saude.repository.UsuarioRepository;

@ControllerAdvice
public class LoggedUserController {




@Autowired
UsuarioRepository usuarioRepository;

@ModelAttribute("usuarioLogado")
public UsuarioModel addModel() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
        String cpfLogado = authentication.getName();
        return usuarioRepository.findByCpf(cpfLogado);
    }
    return null;
}
}