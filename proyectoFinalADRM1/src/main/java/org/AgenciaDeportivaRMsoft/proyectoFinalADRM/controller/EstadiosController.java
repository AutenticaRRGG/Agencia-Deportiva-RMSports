package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.controller;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Estadio;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceDeportistas;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceEstadios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estadios")
public class EstadiosController {
	@Autowired
	private IntServiceEstadios serviceEstadios;
	@GetMapping("/buscar")
	public String buscar (@RequestParam("id") int idEstadio,Model model) {
		Estadio estadio= serviceEstadios.buscarPorId(idEstadio);
		model.addAttribute("deportistas", serviceDeportistas.obtenerDeportistas());
		model.addAttribute("estadio", estadio);
		return "estadios/formEstadio";
	}
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id")int idEstadio, RedirectAttributes model ){
		serviceEstadios.eliminar(idEstadio);
		model.addFlashAttribute("msg","Estadio eliminado");
		return "redirect:/estadios/indexPaginado";
		
	}
	
	
	@GetMapping("/detalle")
	public String detalle(@RequestParam("id")int idEstadio, Model model) {
		Estadio estadio=serviceEstadios.buscarPorId(idEstadio);
		model.addAttribute("estadio",estadio);
		return "estadios/detalle";
	}
	
	@Autowired
	private IntServiceDeportistas serviceDeportistas;
	
	@GetMapping("/nueva")
	public String nuevoEstadio(Estadio estadio,Model model) {
		model.addAttribute("deportistas",serviceDeportistas.obtenerDeportistas());
		return"estadios/formEstadio";
	}
	
	@PostMapping("/guardar")
	public String guardar(Estadio estadio,RedirectAttributes model) {
	serviceEstadios.guardar(estadio);
		return "redirect:/estadios/indexPaginado";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("estadios", serviceEstadios.obtenerEstadios());
		return"estadios/listaEstadios";
	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Estadio> lista = serviceEstadios.buscarTodas(page); 
	model.addAttribute("estadios", lista);
	return "estadios/listaEstadios";
	}
}

