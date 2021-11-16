package com.curso.controller;

import javax.servlet.http.HttpServletRequest;
import com.curso.excepciones.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import com.curso.service.ProductoService;

@Controller
@RequestMapping("comercio")
public class ProductosController {

	@Autowired
	private ProductoRepository productoRepositorio;

	@Autowired
	private ProductoService productoService;

	@RequestMapping(path = "/productos", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("productos", productoRepositorio.getAllProductos());

		return "productos";
	}

	@GetMapping("/productos/{categoria}")
	public String getProductosPorCategoria(Model model, @PathVariable("categoria") String categoriaProducto) {

		model.addAttribute("productos", productoService.getProductosPorCategoria(categoriaProducto));
		return "productos";
	}
	
	// mostra el fomulario con un producto vacio para 
	// que el cliente rellene los datos 
	@GetMapping(value = "/productos/nuevo")
	public String getCrearNuevoProductoFormulario(Model model) {
	
		System.out.println(".... nuevo");
		Producto nuevoProducto = new Producto();
		nuevoProducto.setDescripcion("**** nuevo ****");
		
		model.addAttribute("nuevoProducto", nuevoProducto);
		return "crear-producto";
	}

	// tratara los datos recibidos del formulario
	
		@PostMapping(value = "/productos/nuevo")
		public String procesarCrearNuevoProductoFormulario(
				@ModelAttribute("nuevoProducto") Producto nuevoProducto) throws GestionProductoException {
			
			System.out.println("producto nuevo " + nuevoProducto.getDescripcion());

		    productoService.crearProducto(nuevoProducto);

			return "redirect:/comercio/productos"; 
		}
	
	
	
		@ExceptionHandler(GestionProductoException.class)
	    public ModelAndView handleError(
	    		HttpServletRequest req,
	    		GestionProductoException exception) {
	 
	        ModelAndView mav = new ModelAndView();
	        mav.addObject("idProductoNoEncontrado", 
	                exception.getIdProducto());
	        mav.addObject("claveMensage", 
	                exception.getMessage());
	        
	        mav.setViewName("producto-exception");
	        return mav;
	    }
		
	
	
	

}
