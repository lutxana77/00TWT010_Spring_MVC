package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.domain.Country;
import com.curso.domain.Producto;
import com.curso.domain.Region;
import com.curso.domain.repository.RegionRepository;
import com.curso.excepciones.GestionProductoException;
import com.curso.excepciones.GestionRegionException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RegionPaisServiceImpl implements RegionPaisService{
	
	
	
	
	@Autowired
	@Qualifier("JPAregionesRepository")
    private RegionRepository regionRepositorio;
	
	
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Region> getTodasRegiones() {
    	
       return regionRepositorio.getAllRegiones();
    }



    

    
	@Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Region getRegionPorId(long idRegion) {
    	Region region =regionRepositorio.getRegionPorId(idRegion);
      return region;
    }






	@Override
	public List<Country> getAllPaisesPorRegion(long idRegion) {
		Region region =regionRepositorio.getRegionPorId(idRegion);
		
		
		return region.getCountries();
	}
	
    @Override
    public void crearPais(Country pais)  {
  

    	regionRepositorio.addPais(pais);
    	
    }
	
	

	

}
