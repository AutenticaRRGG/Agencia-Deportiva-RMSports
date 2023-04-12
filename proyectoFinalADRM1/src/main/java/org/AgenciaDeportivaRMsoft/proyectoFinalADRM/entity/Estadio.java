package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Estadios")
//Pojo o JavaBeen
public class Estadio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String nombre;
	private String descripcion;
	private String estado;
	private String capacidad;
	
	// Si no se tienen la notacion permite que la app siga funcionando correctamente
	// @Transient
	@OneToOne
	@JoinColumn(name = "idCategoria")
	private Deportista categoria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}


	

	public Deportista getCategoria() {
		return categoria;
	}

	public void setCategoria(Deportista categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Estadio [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", capacidad=" + capacidad + ", categoria=" + categoria + "]";
	}

}
