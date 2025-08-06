package com.Mais_Saude.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Mais_Saude.model.PermissoesModel;
import com.Mais_Saude.model.UsuarioModel;
import com.Mais_Saude.repository.PermissoesRepository;
import com.Mais_Saude.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissoesRepository permissoesRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;

    public String listarUsuarios(Model model){
        
		model.addAttribute("usuarios", usuarioRepository.findAll());
		model.addAttribute("permissoes", permissoesRepository.findAll());

		return"/usuarios/listar-usuarios";


    }
	@Transactional
    public String buscarUsuario(long id,Model model,RedirectAttributes redirectAttributes){

        if (usuarioRepository.existsById(id)) {
				
		UsuarioModel usuario =  usuarioRepository.findById(id).get();

		List<Long> permissoesId = new ArrayList<>();

		for (PermissoesModel permissao : usuario.getPermissoes()) {
    	permissoesId.add(permissao.getId());
		}

		model.addAttribute("permissoesIds", permissoesId);
	    model.addAttribute("usuarios", usuario);
		model.addAttribute("permissoes", permissoesRepository.findAll());
	     
		return ("/usuarios/alterar");
	     }
		else{
			redirectAttributes.addFlashAttribute("mensagemErro", "Ação não permitida");
		    return ("redirect:/usuarios");
		  }

    }


    public String criarUsuario(UsuarioModel usuario,RedirectAttributes redirectAttributes){

			
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuarioRepository.save(usuario);
			redirectAttributes.addFlashAttribute("mensagem", "Usuario criado com sucesso ");
			return "redirect:/usuarios";

    }


    public String atulizarUsuario (long id, UsuarioModel usuario,RedirectAttributes redirectAttributes){
        if(!usuarioRepository.existsById(id)){
		redirectAttributes.addFlashAttribute("mensagemErro", "Ação não permitida, Usuario não encontrado");

	      return "redirect:/usuarios";
	    }
		//gambiarra antes de fazer DTO
		UsuarioModel dadosUsuario = usuarioRepository.findById(id).get();

		usuario.setSenha(dadosUsuario.getSenha());
		UsuarioModel usuarioSalvo = usuarioRepository.save(usuario);

		redirectAttributes.addFlashAttribute("mensagem", "Usuario : "+usuarioSalvo.getNome() +" Alterado com sucesso!");

	    return "redirect:/usuarios";
    }

    public String deletarUsuario(long id,RedirectAttributes redirectAttributes){
	// quando estiver com o security adicionar antes da verificação de senha//
		if (usuarioRepository.existsById(id)) {
			 
		 UsuarioModel usuarioDeletado = usuarioRepository.findById(id).get();
		 usuarioRepository.delete(usuarioDeletado);
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
