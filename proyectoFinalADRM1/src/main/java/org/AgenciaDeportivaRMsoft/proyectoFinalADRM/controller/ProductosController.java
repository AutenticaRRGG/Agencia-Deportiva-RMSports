package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.controller;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Producto;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceDeportistas;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceProductos;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
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
@Controller

@RequestMapping("/productos")
public class ProductosController {
	@Autowired
	private IntServiceProductos serviceProductos;
	@GetMapping("/buscar")
	public String buscar (@RequestParam("id") int idProducto,Model model) {
		Producto producto= serviceProductos.buscarPorId(idProducto);
		model.addAttribute("productos", serviceDeportistas.obtenerDeportistas());
		model.addAttribute("producto", producto);
		return "productos/formProducto";
	}
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id")int idProducto, RedirectAttributes model ){
		serviceProductos.eliminar(idProducto);
		model.addFlashAttribute("msg","Producto eliminado");
		return "redirect:/productos/indexPaginado";
		
	}
	
	
	@GetMapping("/detalle")
	public String detalle(@RequestParam("id")int idProducto, Model model) {
		Producto producto=serviceProductos.buscarPorId(idProducto);
		model.addAttribute("producto", producto);
		return "productos/detalle";
	}
	
	@Autowired
	private IntServiceDeportistas serviceDeportistas;
	
	@GetMapping("/nueva")
	public String nuevoProducto(Producto producto,Model model) {
		model.addAttribute("deportistas",serviceDeportistas.obtenerDeportistas());
		return"productos/formProducto";
	}
	
	
	
	@PostMapping("/guardar")
	public String  guardar(Producto producto,BindingResult result, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes model) {
		System.out.println(producto);
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}
			model.addAttribute("deportistas",serviceDeportistas.obtenerDeportistas());
			return"productos/formProducto";
		}
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			String ruta = "c:/agencia/img-productos/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
			producto.setImagen(nombreImagen); 
			}

		}
		if(producto.getId()==null)
			model.addFlashAttribute("msg","Producto agregada");
		else model.addFlashAttribute("msg","Producto modificada");
		serviceProductos.guardar(producto);
		return "redirect:/productos/indexPaginado";
		
		
	}
	
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("productos", serviceProductos.obtenerProductos());
		return"productos/listaProductos";
	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Producto> lista = serviceProductos.buscarTodas(page); 
	model.addAttribute("productos", lista);
	return "productos/listaProductos";
	}
}