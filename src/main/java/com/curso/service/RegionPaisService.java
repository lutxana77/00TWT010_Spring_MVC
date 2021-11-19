package com.curso.service;

import java.util.List;

import com.curso.domain.Country;
import com.curso.domain.Region;
import com.curso.excepciones.GestionRegionException;

public interface RegionPaisService {
	
	List<Region> getTodasRegiones();

	Region getRegionPorId(long idRegion);
	
	List<Country> getAllPaisesPorRegion(long idRegion);

	void crearPais(Country pais) ;

}
