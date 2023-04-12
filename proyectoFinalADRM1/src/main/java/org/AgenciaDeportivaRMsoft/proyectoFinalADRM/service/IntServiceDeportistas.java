package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service;

import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Deportista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceDeportistas {
	public List<Deportista> obtenerDeportistas();
	public void guardar(Deportista deportista);
	public Deportista buscarPorId(Integer idDeportista);
	public void eliminar(Integer idDeportista);
	public int numeroDeportistas();
	Page<Deportista>buscarTodas(Pageable page);
	public List<Deportista> findAll();
}