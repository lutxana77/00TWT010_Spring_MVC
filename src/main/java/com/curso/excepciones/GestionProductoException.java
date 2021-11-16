package com.curso.excepciones;

public class GestionProductoException extends Exception {

	
	private String idProducto;

	public GestionProductoException(String idProd, String message) {
		super(message);
		this.idProducto = idProd;
		
	}
	
	public String getIdProducto() {
		return idProducto;
	}
	
}
