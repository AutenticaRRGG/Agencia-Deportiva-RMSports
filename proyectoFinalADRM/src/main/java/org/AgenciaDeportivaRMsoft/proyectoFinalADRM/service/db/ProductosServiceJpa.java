package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.db;

import java.util.List;
import java.util.Optional;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Producto;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.repository.ProductosRepository;
import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service.IntServiceProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ProductosServiceJpa implements IntServiceProductos{
	@Autowired
	private ProductosRepository repoProductos;

	@Override
	public List<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
		return repoProductos.findAll();
	}

	@Override
	public void guardar(Producto producto) {
		// TODO Auto-generated method stub
		repoProductos.save(producto);
	}

	@Override
	public void eliminar(Integer idProducto) {
		// TODO Auto-generated method stub
		repoProductos.deleteById(idProducto);
	}

	@Override
	public Producto buscarPorId(Integer idProducto) {
		// TODO Auto-generated method stub
		Optional<Producto> optional=repoProductos.findById(idProducto);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public long totalProductos() {
		// TODO Auto-generated method stub
		return repoProductos.count();
	}

	@Override
	public Page<Producto> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repoProductos.findAll(page);
	}


}