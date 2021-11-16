
package com.curso.service;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import com.curso.excepciones.GestionProductoException;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
     @Autowired
    private ProductoRepository productoRepositorio;

   
    
    @Override
    public List<Producto> getTodosProductos() {
       return productoRepositorio.getAllProductos();
    }

    @Override
    public List<Producto> getProductosPorCategoria(String categoria) {
      return productoRepositorio.getProductosPoCategoria(categoria);
    }

    

    @Override
    public Producto getProductoPorId(String idProducto) {
      Producto producto =productoRepositorio.getProductoPorId(idProducto);
      return producto;
    }
  
    
    @Override
    public void crearProducto(Producto producto) throws GestionProductoException {
    	
    	//busco si existe ya
    	Producto p = productoRepositorio.getProductoPorId(producto.getIdProducto());
    	if( p != null) {
	    	throw new GestionProductoException(producto.getIdProducto(),
	     			   "No pudo crear el Producto. Ya existe uno con id ");
    	}

    	productoRepositorio.addProducto(p);
    	
    }
    
}
