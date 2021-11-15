package com.curso.controller;

import java.security.Provider.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.curso.service.PedidoService;

@Controller
public class PedidosController {
	
	@Autowired
	private PedidoService service;
	
	
	@GetMapping("/pedido/{idProducto}/{cantidad}")
	public String pedido(
	    		@PathVariable("idProducto") String idProducto,
	    		@PathVariable("cantidad") String cantidades) {
		int cant = Integer.parseInt(cantidades) ;
		//llamar a l servicio que hace el pedido
		service.generarPedido(idProducto, cant);
		//no llamo a una página jsp 
		//sino que le contesto al navegador del cliente
		// que pida el vía url  "/producto"
		return "redirect:/productos"; 
	}
}
