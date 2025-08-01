package com.Mais_Saude.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Mais_Saude.model.ConsultaModel;
import com.Mais_Saude.service.ConsultaService;

import jakarta.servlet.http.HttpSession;



@Controller
public class ConsultaController {


@Autowired
private ConsultaService consultaService;

@GetMapping({"/consultas"})
public String listarConsultas(Model model) {
	return consultaService.listarConsultas(model);
}

@GetMapping ("/consultar-{id}")
public String consultar(@PathVariable long id, Model model,RedirectAttributes redirectAttributes) {
	
	return consultaService.consultar(id, model, redirectAttributes);

}
@GetMapping("/consulta/{nomeTratado}")
public String ConsultaComNome( String nome,Model model,HttpSession session) {
	
    return consultaService.ConsultaComNome(nome,model,session);
}

@PostMapping("/consulta/realizar-consulta")
	public String salvarConsulta (ConsultaModel consulta,Model model,HttpSession session) {
		System.out.println("entrou no consultar");
	

	return consultaService.salvarConsulta(consulta,model,session);
}

@GetMapping ("/visualizar-{id}")
public String visualizarConsulta(@PathVariable long id, Model model) {
	return consultaService.visualizarConsulta(id, model);
}
}