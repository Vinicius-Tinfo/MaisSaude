package com.Mais_Saude.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Mais_Saude.model.UsuarioModel;
import com.Mais_Saude.repository.UsuarioRepository;
import com.Mais_Saude.service.UsuarioService;




@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuariorepository;


	
	@GetMapping("/usuarios")
		public String listar(Model model) {
			return usuarioService.listarUsuarios(model);
	}
	
	@GetMapping("/cadastro-de-usuario")
		public String NovoUsuario() {
		return"/usuarios/cadastro";
	}

	// melhor esse metodo e adicionar verificações
	@PostMapping("/usuario/criar")
		public ModelAndView criar(UsuarioModel usuarios,RedirectAttributes redirectAttributes) {
			return usuarioService.criarUsuario(usuarios, redirectAttributes);		
	}
	
	//Converter esse metodo para post dentro de um modal


	// quando estiver com o security adicionar antes da verificação de senha//
	@PostMapping("/deletar-usuario/{id}")
	public String Deletar(UsuarioModel usuario,@PathVariable("id") long id ,RedirectAttributes redirectAttributes) {

		return usuarioService.deletarUsuario(usuario, id, redirectAttributes);
	
}
	
	  @GetMapping("usuario/{id}")
	  public String busca(@PathVariable long id, Model model,RedirectAttributes redirectAttributes){

		return usuarioService.buscarUsuario(id, model, redirectAttributes);
		
		} 
	
	
	  @PostMapping("/{id}/atualizar")
	  public String atualizar(@PathVariable long id, UsuarioModel usuarios , RedirectAttributes redirectAttributes){

		return usuarioService.atulizarUsuario(id, usuarios, redirectAttributes);
		
	  }
	  
	
	
}
