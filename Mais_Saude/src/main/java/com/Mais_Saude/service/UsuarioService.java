package com.Mais_Saude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Mais_Saude.model.UsuarioModel;
import com.Mais_Saude.repository.PermissoesRepository;
import com.Mais_Saude.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissoesRepository permissoesRepository;

    public String listarUsuarios(Model model){
        
		model.addAttribute("usuarios", usuarioRepository.findAll());
		model.addAttribute("permissoes", permissoesRepository.findAll());

		return"usuarios/listar-usuarios";


    }

    public String buscarUsuario(long id,Model model,RedirectAttributes redirectAttributes){

        if (usuarioRepository.existsById(id)) {

		UsuarioModel usuario =  usuarioRepository.findById(id).get();
	    model.addAttribute("usuarios", usuario);
	     
		return ("usuarios/alterar");
	     }
		else{
			redirectAttributes.addFlashAttribute("mensagemErro", "Ação não permitida");
		    return ("redirect:/usuarios");
		  }

    }


    public ModelAndView criarUsuario(UsuarioModel usuario,RedirectAttributes redirectAttributes){

        ModelAndView mv = new ModelAndView("redirect:/usuarios");
			usuarioRepository.save(usuario);
			redirectAttributes.addFlashAttribute("mensagem", "Usuario criado com sucesso ");
			return mv;

    }


    public String atulizarUsuario (long id, UsuarioModel usuario,RedirectAttributes redirectAttributes){
        if(!usuarioRepository.existsById(id)){
		redirectAttributes.addFlashAttribute("mensagemErro", "Ação não permitida");

	      return "redirect:/usuarios";
	    }
		UsuarioModel usuarioSalvo = usuarioRepository.save(usuario);
		redirectAttributes.addFlashAttribute("mensagem", "Usuario : "+usuarioSalvo.getNome() +" Alterado com sucesso!");

	    return "redirect:/usuarios";
    }

    public String deletarUsuario(UsuarioModel usuario,long id,RedirectAttributes redirectAttributes){
	// quando estiver com o security adicionar antes da verificação de senha//
		if (usuarioRepository.existsById(id)) {
			 
		 UsuarioModel usuarioDeletado = usuarioRepository.findById(id).get();
		 usuarioRepository.deleteById(id);
		 redirectAttributes.addFlashAttribute("mensagem", "O usuario : "+usuarioDeletado.getNome()+" foi deletado");
		return"redirect:/usuarios";
		}
		else{
			redirectAttributes.addFlashAttribute("mensagemErro", "Usuario não encontrado");
			return"redirect:/usuarios";
		}
	}

	//adcionar o inativar usuario futuramente



}
