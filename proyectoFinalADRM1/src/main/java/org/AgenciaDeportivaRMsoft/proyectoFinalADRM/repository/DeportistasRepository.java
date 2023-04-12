package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.repository;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Deportista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeportistasRepository extends JpaRepository <Deportista, Integer> {
}