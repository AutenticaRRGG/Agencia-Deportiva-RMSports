package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service;

import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IntServiceProductos {
	public List<Producto> obtenerProductos();
	public void guardar(Producto producto);
	public void eliminar(Integer idProducto);
	public Producto buscarPorId(Integer idProducto);
	public long totalProductos();
	Page<Producto>buscarTodas(Pageable page);

}
