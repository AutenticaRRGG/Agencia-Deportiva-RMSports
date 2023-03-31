package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.controller;

import java.time.LocalDate;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Perfil;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Usuario;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceDeportistas;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	private IntServiceUsuarios serviceUsuarios;
	@Autowired
	public IntServiceDeportistas serviceDeportistas;
////@Autowired
//private IntServiceEquipos serviceEquipos;
@Autowired
	private PasswordEncoder passwordEncoder;
	
//@PostMapping("/buscar")
//	public String buscar(Model model, @RequestParam ("id") int idDeportista) {
//		List<Equipo> lista= IntServiceEquipos.buscarTodasCategoria(idDeportista);
//		model.addAttribute("equipos", lista);
//		return "homeEquipos";
//	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "formLogin";
	}

	@GetMapping("/acerca")
	public String acerca() {
		return "acerca";
	}

	@GetMapping("/homeEquipos")
	public String homeEquipos() {
		return "homeEquipos";
	}

	@PostMapping("/guardar")
	public String guardar(Usuario usuario) {
		usuario.setFechaRegistro(LocalDate.now());
		usuario.setEstatus(1);
		String texto = usuario.getPassword();
		String encriptado = passwordEncoder.encode(texto);
		usuario.setPassword(encriptado);
		// usuario.setPassword("{noop}" + usuario.getPassword());
		Perfil per = new Perfil();
		per.setId(3);
		usuario.agregar(per);
		serviceUsuarios.agregar(usuario);
		System.out.println(usuario);
		return "redirect:/";
	}

	@GetMapping("/signup")
	public String signup() {
		return "formRegistro";
	}

	@GetMapping(value = "/")
	public String mostrarIndex(Model model) {

		return "home";
	}
//
//	@GetMapping(value = "/indexPaginado")
//	public String mostrarIndexPaginado(Model model, Pageable page) {
//	Page<Equipo> lista = serviceEquipos.buscarTodas(page); 
//	model.addAttribute("equipos", lista);
//	return "equipos/listaEquipos";
//	}
}
