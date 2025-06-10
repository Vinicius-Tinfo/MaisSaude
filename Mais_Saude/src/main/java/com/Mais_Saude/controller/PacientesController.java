package com.Mais_Saude.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Mais_Saude.repository.PacienteRepository;
import com.Mais_Saude.model.PacientesModel;


@Controller
public class PacientesController {
@Autowired
private PacienteRepository pacientesrepository;

@GetMapping("/pacientes")
public String Pacientes(Model model) {
	model.addAttribute("pacientes", pacientesrepository.findAll());
	return "/paciente/listar-pacientes";
}

// Linkando o java com o html
@GetMapping ("/cadastro-paciente")
public String Novo(Model model) {
	return "/paciente/cadastro-paciente";
}

// Fazendo a postagem das infos cadastrais no SQL
@PostMapping(value="pacientes/cadastrar-paciente")
public ModelAndView PacientesModel(PacientesModel pacientes) {
	ModelAndView mv = new ModelAndView("redirect:/pacientes");
	pacientesrepository.save(pacientes);
	
	return mv;
}
@GetMapping("/deletar-paciente/{id}")
public String Deletar (PacientesModel paciente,@PathVariable("id") long id ) {
paciente = (PacientesModel)this.pacientesrepository.getOne(id);
this.pacientesrepository.delete(paciente);
// pacientesrepository.deleteById(id); trocar aqui futuramente
return"redirect:/pacientes";
}

// Buscando as info do paciente
@GetMapping("paciente-{id}")
public String buscapaciente(@PathVariable long id, Model model) {
	Optional<PacientesModel> pacientes = pacientesrepository.findById(id);
	try {
		model.addAttribute("pacientes", pacientes.get());
	}
	catch(Exception err) {return "redirect:/";}
	return("paciente/alterar-paciente");
}

// Salvando as info do paciente
@PostMapping("/{id}/alterarpaciente")
public String alterarpaciente(@PathVariable long id, PacientesModel pacientes) {
	if(!pacientesrepository.existsById(id)) {
		return "redirect:/";
	}
	pacientesrepository.save(pacientes);
	return "redirect:/pacientes";
}



}
