
package com.curso.service;

import com.curso.domain.Producto;
import com.curso.excepciones.GestionProductoException;

import java.util.List;


public interface ProductoService {
    
    List <Producto> getTodosProductos();
    List<Producto> getProductosPorCategoria(String categoria); 
   // List<Producto> getProductosPorFiltro(Map<String, List<String>> paramsFiltro);
    Producto getProductoPorId(String idProducto);
    
    void crearProducto(Producto p) throws GestionProductoException;
    void modificarProducto(Producto p) throws GestionProductoException;
    
    void cambiarPrecio(List<Producto> productos, double nuevoPrecio) throws GestionProductoException;
    void borrarProducto(String id)  throws GestionProductoException;
}
