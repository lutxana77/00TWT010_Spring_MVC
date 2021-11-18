package com.curso.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.domain.Producto;

@Repository
@Qualifier("JPAProductosRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class JPAProductoRepository  implements ProductoRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Producto> getAllProductos() {
		
		//em.createNativeQuery("SELECT * FROM PRODUCTOS");
		
		Query query = em.createNamedQuery("Producto.findAll");
		return query.getResultList();
	}

	@Override
	public Producto getProductoPorId(String idProducto) {
		return em.find(Producto.class, idProducto);
	}

	@Override
	public List<Producto> getProductosPoCategoria(String categoria) {
		Query query = em.createNamedQuery("Producto.findByCategoria");
		query.setParameter("categoria", categoria);
		return query.getResultList();
	}

	@Override
	public void addProducto(Producto producto) {
		em.persist(producto);
	}

	@Override
	public void modificarProducto(Producto producto) {
		em.merge(producto);
	}

	
	@Override
	public void borrarProducto(String id) {
		Producto p = getProductoPorId(id);
		if(p != null) em.remove(p);
	}
	
	
}
