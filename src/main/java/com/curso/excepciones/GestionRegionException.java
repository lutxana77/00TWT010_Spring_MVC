package com.curso.excepciones;

public class GestionRegionException extends Exception {

	
	private long idRegion;

	public GestionRegionException(long l, String message) {
		super(message);
		this.idRegion = l;
		
	}
	
	public long getIdRegion() {
		return idRegion;
	}
	
}
