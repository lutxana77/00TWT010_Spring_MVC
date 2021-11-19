package com.curso.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.curso.excepciones.GestionProductoException;

@ControllerAdvice
class ProductoRestServiceAdvice {

	@ResponseBody
	@ExceptionHandler(GestionProductoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(GestionProductoException ex) {
		return "{ msg: '"+ ex.getMessage() + " id producto:  "
				+ ex.getIdProducto() +  "}'";
	}
}