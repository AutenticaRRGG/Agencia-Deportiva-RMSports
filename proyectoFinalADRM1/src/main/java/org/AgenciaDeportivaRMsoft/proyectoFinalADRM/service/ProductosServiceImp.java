package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.service;

import java.util.LinkedList;
import java.util.List;

import org.AgenciaDeportivaRMsoft.proyectoFinalADRM.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductosServiceImp implements IntServiceProductos {
private List<Producto> productos;
	
	public ProductosServiceImp(){
		
		productos = new LinkedList<>();
		
		Producto v1=new Producto();
		v1.setId(1);
		v1.setNombre("melanie");
		v1.setDescripcion("equipo");
		v1.setPrecio(80.90);
		v1.setExistencias("sin existencias");
		v1.setImagen("monterrey.png");
		v1.setCantidad(50);
		v1.setTalla("G");
		v1.setClasificacion("Sudaderas");
		
		
		
		
		Producto v2=new Producto();
		v2.setId(1);
		v2.setNombre("melanie");
		v2.setDescripcion("equipo");
		v2.setPrecio(80.90);
		v2.setExistencias("sin existencias");
		v2.setImagen("monterrey.png");
		v2.setCantidad(50);
		v2.setTalla("G");
		v2.setClasificacion("Sudaderas");
		
		productos.add(v1);
		productos.add(v2);
		
	
		
	}

	@Override
	public List<Producto> obtenerProductos() {
		return productos;
	}

	@Override
	public void guardar(Producto producto) {
		productos.add(producto);

	}

	@Override
	public void eliminar(Integer idProducto) {
		productos.remove(buscarPorId(idProducto));

	}

	@Override
	public Producto buscarPorId(Integer idProducto) {
		for(Producto v : productos) {
			if(v.getId() == idProducto) {
				return v;
			}
		}
		return null;
	}

	@Override
	public long totalProductos() {
		// TODO Auto-generated method stub
		return productos.size();
	}

	@Override
	public Page<Producto> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}

