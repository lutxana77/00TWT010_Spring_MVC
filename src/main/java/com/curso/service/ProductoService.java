
package com.curso.service;

import com.curso.domain.Producto;
import java.util.List;
import java.util.Map;


public interface ProductoService {
    
    List <Producto> getTodosProductos();
    List<Producto> getProductosPorCategoria(String categoria); 
   // List<Producto> getProductosPorFiltro(Map<String, List<String>> paramsFiltro);
    Producto getProductoPorId(String idProducto);
}
