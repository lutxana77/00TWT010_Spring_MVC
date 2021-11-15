package com.curso.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import com.curso.service.ProductoService;

@Controller
public class ProductosController {
	
	 @Autowired
	 private ProductoRepository productoRepositorio;

	 @Autowired
	 private ProductoService productoService;

	 @RequestMapping(path = "/productos", 
			         method = RequestMethod.GET)
	 public String list(Model model) {
		   model.addAttribute("productos", 
	                productoRepositorio.getAllProductos());

	        return "productos";
	 }
	 
	 
	  @RequestMapping("/productos/{categoria}")
	    public String getProductosPorCategoria(Model model,
	            @PathVariable("categoria") String categoriaProducto) {
	        
	        model.addAttribute("productos",
	                productoService.getProductosPorCategoria(categoriaProducto));
	        return "productos";
	    }
	 
	

	
}
