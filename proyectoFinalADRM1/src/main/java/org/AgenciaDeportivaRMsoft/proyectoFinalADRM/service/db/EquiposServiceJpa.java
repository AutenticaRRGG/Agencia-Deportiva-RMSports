package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.db;

import java.util.List;
import java.util.Optional;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Equipo;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.repository.EquiposRepository;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceEquipos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EquiposServiceJpa implements IntServiceEquipos {
	@Autowired
	private EquiposRepository repoEquipos;

	@Override
	public List<Equipo> obtenerEquipos() {
		// TODO Auto-generated method stub
		return repoEquipos.findAll();
	}

	@Override
	public void guardar(Equipo equipo) {
		// TODO Auto-generated method stub
		repoEquipos.save(equipo);
	}

	@Override
	public void eliminar(Integer idEquipo) {
		// TODO Auto-generated method stub
		repoEquipos.deleteById(idEquipo);
	}

	@Override
	public Equipo buscarPorId(Integer idEquipo) {
		// TODO Auto-generated method stub
		Optional<Equipo> optional=repoEquipos.findById(idEquipo);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public long totalEquipos() {
		// TODO Auto-generated method stub
		return repoEquipos.count();
	}

	@Override
	public Page<Equipo> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoEquipos.findAll(page);
	}

	@Override
	public List<Equipo> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipo> findAll() {
		// TODO Auto-generated method stub
		return repoEquipos.findAll();
	}

//	@Override
//	public List<Equipo> buscarTodasCategoria(int idDeportista) {
//		// TODO Auto-generated method stub
//		return repoEquipos.buscarPorTodosYPorCategoria(idDeportista);
//	}


}
