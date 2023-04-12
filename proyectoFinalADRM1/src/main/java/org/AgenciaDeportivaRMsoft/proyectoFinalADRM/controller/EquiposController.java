package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.controller;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Equipo;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceDeportistas;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceEquipos;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.Utileria;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.reportes.EquipoExporterExcel;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.reportes.EquipoExporterPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/equipos")
public class EquiposController {
	@Value("${proyectoFinalADRM1.ruta.imagenes}")
	private String ruta;
	@Autowired
	private IntServiceEquipos serviceEquipos;
	@GetMapping("/buscar")
	public String buscar (@RequestParam("id") int idEquipo,Model model) {
		Equipo equipo= serviceEquipos.buscarPorId(idEquipo);
		model.addAttribute("deportistas", serviceDeportistas.obtenerDeportistas());
		model.addAttribute("equipo", equipo);
		return "equipos/formEquipo";
	}
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id")int idEquipo, RedirectAttributes model ){
		serviceEquipos.eliminar(idEquipo);
		model.addFlashAttribute("msg","Equipo eliminada");
		return "redirect:/equipos/indexPaginado";
		
	}
	
	
	@GetMapping("/detalle")
	public String detalle(@RequestParam("id")int idEquipo, Model model) {
		Equipo equipo=serviceEquipos.buscarPorId(idEquipo);
		model.addAttribute("equipo",equipo);
		return "equipos/detalle";
	}
	
	@Autowired
	private IntServiceDeportistas serviceDeportistas;
	
	@GetMapping("/nueva")
	public String nuevoEquipo(Equipo equipo,Model model) {
		model.addAttribute("deportistas",serviceDeportistas.obtenerDeportistas());
		return"equipos/formEquipo";
	}
	
	
	
	@PostMapping("/guardar")
	public String  guardar(Equipo equipo,BindingResult result, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes model) {
		System.out.println(equipo);
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}
			model.addAttribute("deportistas",serviceDeportistas.obtenerDeportistas());
			return"equipos/formEquipo";
		}
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			String ruta = "c:/agencia/archivos/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
			equipo.setImagen(nombreImagen); 
			}

		}
		if(equipo.getId()==null)
			model.addFlashAttribute("msg","Equipo agregada");
		else model.addFlashAttribute("msg","Equipo modificada");
		serviceEquipos.guardar(equipo);
		return "redirect:/equipos/indexPaginado";
		
		
	}
	
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("equipos", serviceEquipos.obtenerEquipos());
		return"equipos/listaEquipos";
	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Equipo> lista = serviceEquipos.buscarTodas(page); 
	model.addAttribute("equipos", lista);
	return "equipos/listaEquipos";
	}
	@GetMapping ("/ExportarPDF")
	public void exportarListadoDeEquiposEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());
         
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Equipos" + fechaActual + ".pdf";
        
        response.setHeader(cabecera, valor);
         
        List<Equipo> equipos = serviceEquipos.findAll();
         
        EquipoExporterPDF exporter = new EquipoExporterPDF(equipos);
        exporter.exportar(response);
		
	}
	@GetMapping ("/ExportarExcel")
	public void exportarListadoDeEquiposEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String fechaActual = dateFormatter.format(new Date());
         
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Equipos" + fechaActual + ".xlsx";
        
        response.setHeader(cabecera, valor);
         
        List<Equipo> equipos = serviceEquipos.findAll();
         
        EquipoExporterExcel exporter = new EquipoExporterExcel(equipos);
        exporter.exportar(response);
		
	}
}