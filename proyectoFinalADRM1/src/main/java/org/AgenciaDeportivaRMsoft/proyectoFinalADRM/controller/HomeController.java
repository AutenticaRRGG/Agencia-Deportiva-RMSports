package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.controller;

import java.time.LocalDate;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Equipo;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Perfil;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Usuario;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceDeportistas;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceEquipos;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceUsuarios;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	private IntServiceUsuarios serviceUsuarios;
	@Autowired
	public IntServiceDeportistas serviceDeportistas;
@Autowired
private IntServiceEquipos serviceEquipos;
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
	@GetMapping("/aplicar")
	public String aplicar() {
		return "aplicar";
	}
	@GetMapping("/acerca")
	public String acerca() {
		return "acerca";
	}
	@GetMapping("/usuarioPerfil")
	public String usuarioPerfil() {
		return "usuarioPerfil";
	}
	
	@GetMapping("/formDeportista")
	public String formDeportista() {
		return "/deportistas/formDeportista";
	}

//	@GetMapping("/homeEquipos")
//	public String homeEquipos() {
//		return "homeEquipos";
//	}

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
//	@GetMapping("/")
//	public String mostrarIndex(Model model) {
//		List <Vacante> lista =  serviceVacantes.buscarDestacadas();
//		List <Categoria> categorias = serviceCategorias.obtenerCategorias();
//		model.addAttribute("vacantes", lista);
//		model.addAttribute("categorias", categorias);
//		return "home";
//	}
//
	@GetMapping(value = "/homeEquipos")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Equipo> lista = serviceEquipos.buscarTodas(page); 
	model.addAttribute("equipos", lista);
	return "homeEquipos";
	}
	
	@Autowired
	private UploadService upload;
	@PostMapping ("/cargar")
	public String carga(@RequestParam("archivos")MultipartFile file, RedirectAttributes ms) {
		upload.save(file);
		ms.addFlashAttribute("mensaje", "Archivo Guardado Correctamente");
		return "redirect:/aplicar";
		
	}
	
}
