package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service;

import java.time.DateTimeException;
import java.util.LinkedList;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Equipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EquiposServiceImp implements IntServiceEquipos {
	private List<Equipo> equipos;
	
	public EquiposServiceImp(){
		
		try {
		equipos = new LinkedList<>();
		Equipo v1=new Equipo();
		v1.setId(1);
		v1.setNombre("melanie");
		v1.setDescripcion("equipo");
		v1.setEstatus("Aprobada");
		v1.setImagen("america.png");
		
		
		Equipo v2=new Equipo();
		v2.setId(1);
		v2.setNombre("rmn");
		v2.setDescripcion("equipo 2 ");
		v2.setEstatus("Aprobada");
		v2.setImagen("monterrey.png");
		
		equipos.add(v1);
		equipos.add(v2);
		}catch(DateTimeException ex){
			System.out.println(ex.getMessage());
			
		}
	}

	@Override
	public List<Equipo> obtenerEquipos() {
		return equipos;
	}

	@Override
	public void guardar(Equipo equipo) {
		equipos.add(equipo);

	}

	@Override
	public void eliminar(Integer idEquipo) {
		equipos.remove(buscarPorId(idEquipo));

	}

	@Override
	public Equipo buscarPorId(Integer idEquipo) {
		for(Equipo v : equipos) {
			if(v.getId() == idEquipo) {
				return v;
			}
		}
		return null;
	}

	@Override
	public long totalEquipos() {
		// TODO Auto-generated method stub
		return equipos.size();
	}

	@Override
	public Page<Equipo> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipo> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Equipo> buscarTodasCategoria(int idDeportista) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
