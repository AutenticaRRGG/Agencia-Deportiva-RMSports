package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String mostrarIndex() {
		return "home";	
	}
	@GetMapping ("/acerca")
	String acerca () {
		return "acerca";
	}
}