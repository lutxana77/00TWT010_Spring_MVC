package com.curso.domain.repository;

import java.util.List;

import com.curso.domain.Producto;

public class JPAProductoRepository  implements ProductoRepository{

	@Override
	public List<Producto> getAllProductos() {
		return null;
	}

	@Override
	public Producto getProductoPorId(String idProducto) {
		return null;
	}

	@Override
	public List<Producto> getProductosPoCategoria(String categoria) {
		return null;
	}

	@Override
	public void addProducto(Producto producto) {
		
	}

	@Override
	public void modificarProducto(Producto producto) {
		
	}

}
