package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Deportista;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceDeportistas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/deportistas")
@Controller
public class DeportistasController {
	@Autowired
	private IntServiceDeportistas serviceDeportistas;
	
	@PostMapping("/guardar")
	public String  guardar(Deportista deportista,RedirectAttributes model) {
		serviceDeportistas.guardar(deportista);
		
		return "redirect:/deportistas/indexPaginado";
	}
	
	@GetMapping("/nueva")
	public String nuevoDeportista(Deportista deportista) {
		return"deportistas/formDeportista";
	}
	
	@GetMapping("/eliminar")
	public String eliminarDeportista(@RequestParam("id") int idDeportista, RedirectAttributes model) {
		serviceDeportistas.eliminar(idDeportista);
		model.addFlashAttribute("msg", "Deportista Eliminado");
		return "redirect:/deportistas/indexPaginado";
		
	}
	@GetMapping("/detalle")
	public String detalle(@RequestParam("id")int idDeportista, Model model) {
		Deportista deportista=serviceDeportistas.buscarPorId(idDeportista);
		model.addAttribute("deportista",deportista);
		return "deportistas/detalle";
	}
	@GetMapping("/buscar")
	public String modificarDeportista(@RequestParam("id")int idDeportista,Model model) {
		Deportista deportista=serviceDeportistas.buscarPorId(idDeportista);
		model.addAttribute("deportista", deportista);
		return "deportistas/formDeportista";
		
	}

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Deportista> deportistas = serviceDeportistas.obtenerDeportistas();
		model.addAttribute("deportistas", deportistas);
		model.addAttribute("total", serviceDeportistas.numeroDeportistas());
		return"deportistas/listaDeportistas";
	}
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Deportista> lista = serviceDeportistas.buscarTodas(page); 
	model.addAttribute("deportistas", lista);
	model.addAttribute("total", serviceDeportistas.numeroDeportistas());
	return "deportistas/listaDeportistas";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
          setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
          return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate) getValue());
        }  
    
      });
	}
}