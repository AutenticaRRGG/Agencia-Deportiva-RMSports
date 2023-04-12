package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.db;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Solicitud;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.repository.SolicitudesRepository;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceSolicitudes;
import org.springframework.beans.factory.annotation.Autowired;

public class SolicitudesServiceJpa implements IntServiceSolicitudes{
	@Autowired
	private SolicitudesRepository repoSolicitudes;

	@Override
	public void guardar(Solicitud solicitud) {
		// TODO Auto-generated method stub
		repoSolicitudes.save(solicitud);
	}

}
