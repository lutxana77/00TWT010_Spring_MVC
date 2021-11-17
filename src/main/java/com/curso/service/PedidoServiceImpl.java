package com.curso.service;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    @Qualifier("JPAProductosRepository")
    private ProductoRepository productoRepositorio;

    @Override
    public void generarPedido(String idProducto, int cantidad) {
        Producto producto = productoRepositorio.getProductoPorId(idProducto);

        if (producto.getUnidadesEnStock()< cantidad) {
            throw new IllegalArgumentException("Fuera de Stock. "
                    + "Hay disponibles actualmente " 
                    + producto.getUnidadesEnStock()  +" unidades.");
        }

        producto.setUnidadesEnStock(producto.getUnidadesEnStock()- cantidad);

    }

}
