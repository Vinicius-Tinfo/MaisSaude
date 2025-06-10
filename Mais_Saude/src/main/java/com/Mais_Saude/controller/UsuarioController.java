package com.Mais_Saude.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Mais_Saude.model.UsuarioModel;
import com.Mais_Saude.repository.PermissoesRepository;
import com.Mais_Saude.repository.UsuarioRepository;




@Controller
public class UsuarioController {


	@Autowired
	private UsuarioRepository usuariorepository;

	@Autowired
	private PermissoesRepository permissoesRepository;
	
	@GetMapping({"/usuarios"})
		public String Usuarios(Model model) {
		
			
			

			model.addAttribute("usuarios", usuariorepository.findAll());
						model.addAttribute("permissoes", permissoesRepository.findAll());

		return"usuarios/listar-usuarios";
	}
	
	@GetMapping({"/cadastro-de-usuario"})
		public String NovoUsuario() {
		return"/usuarios/cadastro";
	}
	
	@PostMapping(value="/usuario/criar")
		public ModelAndView UsuarioModel(UsuarioModel usuarios) {
			ModelAndView mv = new ModelAndView("redirect:/usuarios");
			usuariorepository.save(usuarios);
			return mv;
	}
	
	@GetMapping("/deletar-usuario/{id}")
	public String Deletar(UsuarioModel usuario,@PathVariable("id") long id ) {
	usuario = (UsuarioModel)this.usuariorepository.getOne(id);
	this.usuariorepository.delete(usuario);

	return"redirect:/usuarios";
}
		//mexendo aqui 09/06//
	  @GetMapping("usuario/{id}")
	  public String busca(@PathVariable long id, Model model,RedirectAttributes redirectAttributes){

		if (usuariorepository.existsById(id)) {
			
	    Optional<UsuarioModel> usuarios = usuariorepository.findById(id);
		UsuarioModel usuario = usuarios.get();

		System.out.println(usuario.getData_de_nascimento());

	    model.addAttribute("usuarios", usuario);
	     
		return ("usuarios/alterar");
	     }
		else{
			redirectAttributes.addFlashAttribute("mensagemErro", "Ação não permitida");

			System.out.println("entrou no erro");
		    return ("redirect:/usuarios");
		  }
		} 
	
	
	  @PostMapping("/{id}/atualizar")
	  public String atualizar(@PathVariable long id, UsuarioModel usuarios , RedirectAttributes redirectAttributes){
	    if(!usuariorepository.existsById(id)){
	      return "redirect:/";
	    }
		UsuarioModel usuarioSalvo = usuariorepository.save(usuarios);
		redirectAttributes.addFlashAttribute("mensagem", "Usuario : "+usuarioSalvo.getNome() +"Alterado com sucesso!");
	    return "redirect:/usuarios";
	  }
	  
	
	
}
