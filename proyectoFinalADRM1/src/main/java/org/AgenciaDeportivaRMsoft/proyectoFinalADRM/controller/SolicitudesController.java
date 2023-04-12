//package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.controller;
//
//import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Solicitud;
//import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceSolicitudes;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@RequestMapping("/aplicar")
//@Controller
//public class SolicitudesController {
//	@Autowired
//	private IntServiceSolicitudes serviceSolicitudes;
//	@PostMapping("/guardar")
//	public String guardar(Solicitud solicitud,RedirectAttributes model) {
//	serviceSolicitudes.guardar(solicitud);
//		return "redirect:/aplicar";
//	}
//	
////	@Autowired
////	private UploadService upload;
////	@PostMapping ("/cargar")
////	public String carga(@RequestParam("archivos")MultipartFile file, RedirectAttributes ms) {
////		upload.save(file);
////		ms.addFlashAttribute("mensaje", "Archivo Guardado Correctamente");
////		return "redirect:/aplicar";
////		
////	}
////	
//}