package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="Equipos")
public class Equipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private String estatus;
	private String email;
	private String imagen="logoRMsoft.png";
	//Si no se tienen la notacion permite que la app siga funcionando correctamente @Transient
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
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Deportista getCategoria() {
		return categoria;
	}
	public void setCategoria(Deportista categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Equipo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estatus=" + estatus
				+ ", email=" + email + ", imagen=" + imagen + ", categoria=" + categoria + "]";
	}
}