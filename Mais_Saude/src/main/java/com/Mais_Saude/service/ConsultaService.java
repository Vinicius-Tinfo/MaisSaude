package com.Mais_Saude.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Mais_Saude.model.ConsultaModel;
import com.Mais_Saude.model.PacientesModel;
import com.Mais_Saude.repository.ConsultaRepository;
import com.Mais_Saude.repository.PacienteRepository;

import jakarta.servlet.http.HttpSession;

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

		
		
	String nomeTratado = paciente.getNome().replace(" ", "-");
	return ("redirect:/consulta/"+nomeTratado);

	// return "/consulta/consultar";
	}
	catch(Exception err) { return "redirect:/pacientes";}
	}


public String ConsultaComNome( String nome,Model model,HttpSession session) {
	
	PacientesModel paciente = (PacientesModel)model.getAttribute("pacientes");

	session.setAttribute("pacienteId", paciente.getId());


    return "/consulta/consultar";
}

public String salvarConsulta (ConsultaModel consulta,Model model,HttpSession session) {
	LocalDateTime data = LocalDateTime.now();;
	System.out.println("entrou");

	Long pacienteId = (Long) session.getAttribute("pacienteId");

	System.out.println("pegou no session "+pacienteId);

	PacientesModel paciente =  pacienterepository.findById(pacienteId).get();

	consulta.setData(data);
	consulta.setPaciente(paciente);
	consultarepository.save(consulta);





	return "redirect:/pacientes";

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