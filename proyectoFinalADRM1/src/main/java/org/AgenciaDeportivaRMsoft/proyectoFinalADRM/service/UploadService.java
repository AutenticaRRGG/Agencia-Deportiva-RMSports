package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	String ruta = "c:/agencia/archivos/";
	private final Logger logg = LoggerFactory.getLogger(UploadService.class);
	
	public String save (MultipartFile multiPart ) {
		if(!multiPart.isEmpty()) {
			try {
				byte[] bytes = multiPart.getBytes();
				Path path = Paths.get(ruta+multiPart.getOriginalFilename());
				Files.write(path, bytes);
				logg.info("Archivo guardado");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		}
		return "Archivo Guardado Correctamente";
	}
	

}
