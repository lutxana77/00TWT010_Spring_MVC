package com.curso.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.domain.Country;
import com.curso.domain.Producto;
import com.curso.domain.Region;

@Repository
@Qualifier("JPAregionesRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class JPARegionRepository  implements RegionRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Region> getAllRegiones() {
		
		
		Query query = em.createNamedQuery("Region.findAll");
		return query.getResultList();
	}

	
	@Override
	public Region getRegionPorId(long idRegion) {
		return em.find(Region.class, idRegion);
	}


	@Override
	public void addPais(Country pais) {
		em.persist(pais);
	}



	
	
}
