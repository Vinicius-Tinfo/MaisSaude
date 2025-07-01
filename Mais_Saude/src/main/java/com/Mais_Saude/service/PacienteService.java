package com.Mais_Saude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Mais_Saude.model.PacientesModel;
import com.Mais_Saude.repository.PacienteRepository;

@Service
public class PacienteService {
    

    @Autowired
    private PacienteRepository pacienteRepository;


public String listarPacientes(Model model){
    model.addAttribute("pacientes", pacienteRepository.findAll());
	return "/paciente/listar-pacientes";

}

public String buscarPaciente(long id,Model model,RedirectAttributes redirectAttributes){

    if (pacienteRepository.existsById(id)){
        PacientesModel paciente = pacienteRepository.findById(id).get();

        model.addAttribute("pacientes", paciente);

        return "paciente/alterar-paciente";
    }else{

        redirectAttributes.addAttribute("mensagemErro", "Ação não permitida");
        return"redirect:/pacientes";
    }

}
//adicionar verificação futura se ja existe o paciente//
public ModelAndView criarPaciente(PacientesModel paciente,RedirectAttributes redirectAttributes){
    ModelAndView mv = new ModelAndView("redirect:/pacientes");
    pacienteRepository.save(paciente);
    redirectAttributes.addFlashAttribute("mensagem", "Paciente criado com sucesso ");
    return mv;


}

public String atulizarPaciente(long id,PacientesModel paciente,RedirectAttributes redirectAttributes){
    if (!pacienteRepository.existsById(id)) {
        redirectAttributes.addFlashAttribute("mensagemErro", "Ação não permitida, Paciente não encontrado");
        
        return"redirect/pacientes";
    }
    PacientesModel pacienteSalvo = pacienteRepository.save(paciente);
    redirectAttributes.addFlashAttribute("mensagem", "Paciente : "+pacienteSalvo.getNome() +" Alterado com sucesso!");
    
          return"redirect:/pacientes";
    }

  


public String deletarPaciente(long id,RedirectAttributes redirectAttributes){
    if (pacienteRepository.existsById(id)) {
        PacientesModel pacienteDeletado = pacienteRepository.findById(id).get();
        pacienteRepository.delete(pacienteDeletado);
        redirectAttributes.addFlashAttribute("mensagem", "O Paciente : "+pacienteDeletado.getNome()+" foi deletado");
        return"redirect/pacientes";
        
    }else{
         redirectAttributes.addFlashAttribute("mensagemErro", "O Paciente não foi encontrado");
        return"redirect/pacientes";
    }
}


// listar/buscar/criar/atulizar/deletar




}