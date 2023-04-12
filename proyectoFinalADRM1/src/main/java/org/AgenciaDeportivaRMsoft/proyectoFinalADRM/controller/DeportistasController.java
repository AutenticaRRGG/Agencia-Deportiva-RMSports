package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Deportista;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceDeportistas;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.Utileria;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.reportes.DeportistaExporterExcel;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.reportes.DeportistaExporterPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/deportistas")
@Controller
public class DeportistasController {
	@Autowired
	private IntServiceDeportistas serviceDeportistas;
	
	@PostMapping("/guardar")
	public String  guardar(Deportista deportista,BindingResult result, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes model) {
		System.out.println(deportista);
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}
			model.addAttribute("deportistas",serviceDeportistas.obtenerDeportistas());
			return"deportistas/formDeportista";
		}
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			String ruta = "c:/agencia/img-deportistas/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
		deportista.setImagen(nombreImagen); 
			}

		}
		if(deportista.getId()==null)
			model.addFlashAttribute("msg","Libro agregada");
		else model.addFlashAttribute("msg","Lbro modificada");
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
	@GetMapping ("/ExportarPDF")
	public void exportarListadoDeDeportistasEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date(0));
         
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Deportistas" + fechaActual + ".pdf";
        
        response.setHeader(cabecera, valor);
         
        List<Deportista> deportistas = serviceDeportistas.findAll();
         
        DeportistaExporterPDF exporter = new DeportistaExporterPDF(deportistas);
        exporter.exportar(response);
		
	}
	@GetMapping ("/ExportarExcel")
	public void exportarListadoDeDeportistasEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date(2023));
         
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Deportistas" + fechaActual + ".xlsx";
        
        response.setHeader(cabecera, valor);
         
        List<Deportista> deportistas = serviceDeportistas.findAll();
         
        DeportistaExporterExcel exporter = new DeportistaExporterExcel(deportistas);
        exporter.exportar(response);
	}
}