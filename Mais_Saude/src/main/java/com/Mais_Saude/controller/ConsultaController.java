package com.Mais_Saude.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Mais_Saude.model.ConsultaModel;
import com.Mais_Saude.model.PacientesModel;
import com.Mais_Saude.repository.ConsultaRepository;
import com.Mais_Saude.repository.PacienteRepository;
import com.Mais_Saude.service.ConsultaService;



@Controller
public class ConsultaController {
@Autowired
private ConsultaRepository consultarepository;
@Autowired
private PacienteRepository pacienterepository;

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
public String ConsultaComNome( String nome,Model model) {

    return consultaService.ConsultaComNome(nome,model);
}

@PostMapping(value="consulta/realizar-consulta")
	public ModelAndView salvarConsulta (ConsultaModel consulta) {
	return consultaService.salvarConsulta(consulta);
}

@GetMapping ("/visualizar-{id}")
public String visualizarConsulta(@PathVariable long id, Model model) {
	return consultaService.visualizarConsulta(id, model);
}
}