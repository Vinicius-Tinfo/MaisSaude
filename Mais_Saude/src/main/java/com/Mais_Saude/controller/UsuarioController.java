package com.Mais_Saude.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Mais_Saude.model.UsuarioModel;
import com.Mais_Saude.repository.PermissoesRepository;
import com.Mais_Saude.service.UsuarioService;




@Controller
public class UsuarioController {


@Autowired
private PermissoesRepository permissoesRepository;

@Autowired
private UsuarioService usuarioService;

		
@GetMapping("/usuarios")
public String listar(Model model) {
	return usuarioService.listarUsuarios(model);
	}
	
@GetMapping("/cadastro-de-usuario")
public String novoUsuario(Model model) { 
	model.addAttribute("permissoes", permissoesRepository.findAll());

	return"/usuarios/cadastro";
	}

	// melhor esse metodo e adicionar verificações
@PostMapping("/usuario/criar")
	public String criar(UsuarioModel usuarios,RedirectAttributes redirectAttributes) {

	return usuarioService.criarUsuario(usuarios, redirectAttributes);		
	}
	
	//Converter esse metodo para post dentro de um modal
	// quando estiver com o security adicionar antes da verificação de senha//
@PostMapping("/deletar-usuario/{id}")
	public String Deletar(@PathVariable("id") long id ,RedirectAttributes redirectAttributes) {

    return usuarioService.deletarUsuario(id, redirectAttributes);
	}

@GetMapping("usuario/{id}")
	public String busca(@PathVariable long id, Model model,RedirectAttributes redirectAttributes){

	return usuarioService.buscarUsuario(id, model, redirectAttributes);
		
	 } 
	
	
@PostMapping("/usuario/{id}/atualizar")
	public String atualizar(@PathVariable long id, UsuarioModel usuarios , RedirectAttributes redirectAttributes){

	return usuarioService.atulizarUsuario(id, usuarios, redirectAttributes);
		
	}
	  
	
	
}
