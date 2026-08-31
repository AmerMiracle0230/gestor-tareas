package com.example.gestor_tareas.exception;

//error 404
public class NotFoundException extends RuntimeException{

	public NotFoundException(String mensaje) {
		super(mensaje);
	}
}
