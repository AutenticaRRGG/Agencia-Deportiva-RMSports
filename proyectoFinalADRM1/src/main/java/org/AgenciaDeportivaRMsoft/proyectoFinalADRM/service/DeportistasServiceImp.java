package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Deportista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeportistasServiceImp implements IntServiceDeportistas {
private List<Deportista> deportistas = null;
	
	public DeportistasServiceImp() {
		DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			
		deportistas = new LinkedList<>();
		
		Deportista c1 = new Deportista();
		c1.setId(1);
		c1.setNombre("Ingenieros");
		c1.setCategoria("Futbol");
		c1.setPosicion("medio");
		c1.setFechaNacimiento(LocalDate.parse("06/02/2023", formato));
		c1.setPeso(70);
		c1.setPeso(1.80);
		c1.setTelefono("42424242");
		c1.setEmail("ramon.granados@gj.com");
		c1.setDescripcion("Ingenieros");
		c1.setImagen("america.png");
		
		
	
		
		Deportista c2 = new Deportista();
		c2.setId(1);
		c2.setNombre("Ingenieros");
		c2.setCategoria("Futbol");
		c2.setPosicion("medio");
		c2.setFechaNacimiento(LocalDate.parse("06/02/2023", formato));
		c2.setPeso(70);
		c2.setPeso(1.80);
		c2.setTelefono("42424242");
		c2.setEmail("ramon.granados@gj.com");
		c2.setDescripcion("Ingenieros");
		c2.setImagen("america.png");
		
		
		deportistas.add(c1);
		deportistas.add(c2);
		}catch(DateTimeException ex){
			System.out.println(ex.getMessage());
			
	}
	}

	@Override
	public List<Deportista> obtenerDeportistas() {
		return deportistas;
	}

	@Override
	public void guardar(Deportista deportista) {
		deportistas.add(deportista);

	}

	@Override
	public Deportista buscarPorId(Integer idDeportista) {
		for(Deportista cat : deportistas) {
			if(cat.getId().compareTo(idDeportista) == 0) {
				return cat;
			}
		}
		return null;
	}

	@Override
	public void eliminar(Integer idDeportista) {
		deportistas.remove(buscarPorId(idDeportista));

	}

	
	@Override
	public int numeroDeportistas(){
		return deportistas.size();
	}

	@Override
	public Page<Deportista> buscarTodas(Pageable page) {
		return null;
	}

	@Override
	public List<Deportista> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}