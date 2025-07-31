package com.Mais_Saude.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

@Service
public class ConsultaService {


@Autowired
private ConsultaRepository consultarepository;
@Autowired
private PacienteRepository pacienterepository;



public String listarConsultas(Model model) {
	model.addAttribute("consulta", consultarepository.findAll());
return "/consulta/listar-consultas";
}

public String consultar(@PathVariable long id, Model model,RedirectAttributes redirectAttributes) {
	try {
		PacientesModel paciente = pacienterepository.findById(id).get();
		redirectAttributes.addFlashAttribute("pacientes", paciente);
			// model.addAttribute("pacientes", pacientes.get());
		
	String nomeTratado = paciente.getNome().replace(" ", "-");
	return ("redirect:/consulta/"+nomeTratado);

	// return "/consulta/consultar";
	}
	catch(Exception err) { return "redirect:/pacientes";}
	}


public String ConsultaComNome( String nome,Model model) {

    return "/consulta/consultar";
}

public ModelAndView salvarConsulta (ConsultaModel consulta) {
	ModelAndView mv = new ModelAndView("redirect:/pacientes");
	consultarepository.save(consulta);
	return mv;

}

public String visualizarConsulta(@PathVariable long id, Model model) {
	Optional<ConsultaModel> consulta = consultarepository.findById(id);
	try {
		model.addAttribute("consulta", consulta.get());
	}
	catch(Exception err) { return "redirect:/consultas";}
	
	return "/consulta/visualizar-consulta";
	}
}