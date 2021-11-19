
package com.curso.service;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import com.curso.excepciones.GestionProductoException;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProductoServiceImpl implements ProductoService {
   
	@Autowired
	@Qualifier("JPAProductosRepository")
    private ProductoRepository productoRepositorio;

   
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Producto> getTodosProductos() {
       return productoRepositorio.getAllProductos();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Producto> getProductosPorCategoria(String categoria) {
      return productoRepositorio.getProductosPoCategoria(categoria);
    }

    

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
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

    	productoRepositorio.addProducto(producto);
    	
    }

	@Override
	public void modificarProducto(Producto producto) throws GestionProductoException {
		//busco si existe ya
    	Producto p = productoRepositorio.getProductoPorId(producto.getIdProducto());
    	if( p == null) {
	    	throw new GestionProductoException(producto.getIdProducto(),
	     			   "No pudo modificar el Producto. No existe con el id indicado ");
    	}

    	productoRepositorio.modificarProducto(producto);
    	
	}
    
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,
	                rollbackFor = GestionProductoException.class)
	public void cambiarPrecio(List<Producto> productos, double nuevoPrecio) 
			throws GestionProductoException {
	
		
		for(Producto p : productos) {
			if(p.getPrecioUnitario().doubleValue() > nuevoPrecio) {
				throw new GestionProductoException(p.getIdProducto(),
						"No se pudo cambiar el precio ya que el nuevo "
						+ "precio no puede ser menor");
			}
			p.setPrecioUnitario(new BigDecimal(nuevoPrecio));
		}
		//commit  si todo ok
		//rollback si ocurre una excepcion del sistema RunneTimeException
	}

	@Override
	public void borrarProducto(String id) throws GestionProductoException {
		Producto p = productoRepositorio.getProductoPorId(id);
		if (p != null) {
			throw new GestionProductoException(id,
					"No pudo borrar el Producto. No existe con el id indicado ");
		}

		productoRepositorio.borrarProducto(id);

	}
	
	
	
}
