package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Solicitudes")
public class Solicitud {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate fecha = LocalDate.now();
	private String archivo;
	private String comentarios;
	@OneToOne
	@JoinColumn(name = "idDeportista")
	private Deportista idDeportista;
	@OneToOne
	@JoinColumn(name = "idUsuario")
	private Usuario idUsuario;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Deportista getIdDeportista() {
		return idDeportista;
	}
	public void setIdDeportista(Deportista idDeportista) {
		this.idDeportista = idDeportista;
	}
	public Usuario getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}
	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", fecha=" + fecha + ", archivo=" + archivo + ", comentarios=" + comentarios
				+ ", idDeportista=" + idDeportista + ", idUsuario=" + idUsuario + "]";
	}
	
	
}
