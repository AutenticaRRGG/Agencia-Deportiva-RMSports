package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service;

import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Equipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceEquipos {
	public List<Equipo> obtenerEquipos();
	public void guardar(Equipo equipo);
	public void eliminar(Integer idEquipo);
	public Equipo buscarPorId(Integer idEquipo);
	public long totalEquipos();
	Page<Equipo>buscarTodas(Pageable page);
}