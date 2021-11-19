package com.curso.domain.repository;

import com.curso.domain.Country;
import com.curso.domain.Region;
import java.util.List;
import java.util.Map;

public interface RegionRepository {
	
	
     List <Region> getAllRegiones();
    
    Region getRegionPorId(long idRegion);

	void addPais(Country pais);

  
}
