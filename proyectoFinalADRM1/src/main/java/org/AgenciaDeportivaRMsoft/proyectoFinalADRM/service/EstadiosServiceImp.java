package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service;

import java.util.LinkedList;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Estadio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EstadiosServiceImp implements IntServiceEstadios{
private List<Estadio> estadios;
	
	public EstadiosServiceImp(){
		
		estadios = new LinkedList<>();
		Estadio v1=new Estadio();
		v1.setId(1);
		v1.setNombre("melanie");
		v1.setDescripcion("equipo");
		v1.setEstado("Aprobada");
		v1.setCapacidad("america.png");
		
		
		
		Estadio v2=new Estadio();
		v2.setId(1);
		v2.setNombre("melanie");
		v2.setDescripcion("equipo");
		v2.setEstado("Aprobada");
		v2.setCapacidad("america.png");
		
		estadios.add(v1);
		estadios.add(v2);
		
	}

	@Override
	public List<Estadio> obtenerEstadios() {
		return estadios;
	}

	@Override
	public void guardar(Estadio estadio) {
		estadios.add(estadio);

	}

	@Override
	public void eliminar(Integer idEstadio) {
		estadios.remove(buscarPorId(idEstadio));

	}

	@Override
	public Estadio buscarPorId(Integer idEstadio) {
		for(Estadio v : estadios) {
			if(v.getId() == idEstadio) {
				return v;
			}
		}
		return null;
	}

	@Override
	public long totalEstadios() {
		// TODO Auto-generated method stub
		return estadios.size();
	}

	@Override
	public Page<Estadio> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
