package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.db;

import java.util.List;
import java.util.Optional;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Estadio;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.repository.EstadiosRepository;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceEstadios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EstadiosServiceJpa  implements IntServiceEstadios{
	@Autowired
	private EstadiosRepository repoEstadios;

	@Override
	public List<Estadio> obtenerEstadios() {
		// TODO Auto-generated method stub
		return repoEstadios.findAll();
	}

	@Override
	public void guardar(Estadio estadio) {
		// TODO Auto-generated method stub
		repoEstadios.save(estadio);
	}

	@Override
	public void eliminar(Integer idEstadio) {
		// TODO Auto-generated method stub
		repoEstadios.deleteById(idEstadio);
	}

	@Override
	public Estadio buscarPorId(Integer idEstadio) {
		// TODO Auto-generated method stub
		Optional<Estadio> optional=repoEstadios.findById(idEstadio);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public long totalEstadios() {
		// TODO Auto-generated method stub
		return repoEstadios.count();
	}

	@Override
	public Page<Estadio> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoEstadios.findAll(page);
	}


}