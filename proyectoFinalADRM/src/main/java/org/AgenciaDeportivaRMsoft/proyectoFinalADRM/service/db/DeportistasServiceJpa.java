package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.db;

import java.util.List;
import java.util.Optional;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Deportista;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.repository.DeportistasRepository;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceDeportistas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary

public class DeportistasServiceJpa implements IntServiceDeportistas{
	@Autowired      
	private DeportistasRepository repoDeportistas;

	@Override
	public List<Deportista> obtenerDeportistas() {
		return repoDeportistas.findAll();
	}

	@Override
	public void guardar(Deportista deportista) {
		repoDeportistas.save(deportista);

	}

	@Override
	public Deportista buscarPorId(Integer idDeportista) {
		Optional<Deportista> optional = repoDeportistas.findById(idDeportista);
		if(optional.isPresent()) {
		return optional.get();
	}
	return null;
		
	}
	
	

	@Override
	public void eliminar(Integer idDeportista) {
		repoDeportistas.deleteById(idDeportista);

	}

	@Override
	public int numeroDeportistas() {
		return (int) repoDeportistas.count();
		}

	@Override
	public Page<Deportista> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoDeportistas.findAll(page);
	}

}