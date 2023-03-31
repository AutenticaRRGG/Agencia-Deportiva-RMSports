package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service;

import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Estadio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceEstadios {
	public List<Estadio> obtenerEstadios();
	public void guardar(Estadio estadio);
	public void eliminar(Integer idEstadio);
	public Estadio buscarPorId(Integer idEstadio);
	public long totalEstadios();
	Page<Estadio>buscarTodas(Pageable page);

}
