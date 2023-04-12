package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.repository;

import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Equipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquiposRepository extends JpaRepository <Equipo, Integer>{
//	List<Equipo> findByEstatusAndDestacadoOrderByIdDesc(String estatus, int desctado);

//	/* CONSULTAS UTILIZANDO SQL NATIVO */
    @Query(value= "select * from equipos where idDeportista ",nativeQuery = true)
	List<Equipo> buscarPorTodosYPorCategoria(int idDeportista);

//	@Query(value = "select count (*) from vacantes", nativeQuery = true)
//	long numeroElementos();

}

