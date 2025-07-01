package com.Mais_Saude.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Mais_Saude.service.PacienteService;
import com.Mais_Saude.model.PacientesModel;


@Controller
public class PacientesController {



@Autowired
private PacienteService pacienteService;


@GetMapping("/pacientes")
public String Pacientes(Model model) {
	return pacienteService.listarPacientes(model);
}


@GetMapping ("/cadastro-paciente")
public String Novo(Model model) {
	return "/paciente/cadastro-paciente";
}

@GetMapping("paciente/{id}")
public String buscar(@PathVariable long id, Model model,RedirectAttributes redirectAttributes) {
	
	return pacienteService.buscarPaciente(id, model, redirectAttributes);
}

@PostMapping("pacientes/cadastrar-paciente")
public ModelAndView criar(PacientesModel pacientes,RedirectAttributes redirectAttributes) {
	return pacienteService.criarPaciente(pacientes, redirectAttributes);
}


@PostMapping("/{id}/alterarpaciente")
public String alterarpaciente(@PathVariable long id, PacientesModel pacientes,RedirectAttributes redirectAttributes) {
	return pacienteService.atulizarPaciente(id, pacientes, redirectAttributes);
}

@PostMapping("/deletar-paciente/{id}")
public String Deletar (PacientesModel paciente,@PathVariable("id") long id ,RedirectAttributes redirectAttributes) {
	return pacienteService.deletarPaciente(id, redirectAttributes);
}

}
