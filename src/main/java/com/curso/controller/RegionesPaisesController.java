package com.curso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.curso.domain.Country;
import com.curso.domain.Region;
import com.curso.domain.repository.RegionRepository;
import com.curso.excepciones.GestionRegionException;
import com.curso.service.RegionPaisService;

@Controller
@RequestMapping("comercio")
public class RegionesPaisesController {

	@Autowired
	private RegionPaisService service;
	


	@RequestMapping(path = "/regiones", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("regiones", service.getTodasRegiones());

		return "regiones";
	}
	
	@GetMapping("/paises/{idRegion}")
	public String getRegionesPorCategoria(Model model, @PathVariable("idRegion") long idRegion) {
		
		model.addAttribute("region", service.getRegionPorId(idRegion));
		model.addAttribute("paises",service.getAllPaisesPorRegion(idRegion));
		return "paises";
	}
	



//	// mostra el fomulario  vacio para
//	// que el cliente rellene los datos
	@GetMapping(value = "/productos/nuevopais")
	public String getCrearNuevoPaisFormulario(Model model) {

		Country nuevoPais = new Country();
		
		model.addAttribute("nuevoPais", nuevoPais);
		return "crear-pais";
	}

	// tratara los datos recibidos del formulario

	@PostMapping(value = "/productos/nuevopais")
	public String procesarCrearNuevoPaisFormulario(
			@ModelAttribute("nuevoPais") @Valid Country nuevoPais,
			BindingResult bindingResult) throws GestionRegionException {

		// comprobar que es valido
		if (bindingResult.hasErrors()) {
			return "crear-pais"; // no usar redirect se pierden los erros
		}

		service.crearPais(nuevoPais);

		return "redirect:/comercio/regiones";
	}



	@ExceptionHandler(GestionRegionException.class)
	public ModelAndView handleError(HttpServletRequest req, 
			GestionRegionException exception) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("idRegionNoEncontrado", exception.getIdRegion());
		mav.addObject("claveMensage", exception.getMessage());

		mav.setViewName("region-exception");
		return mav;
	}

}

