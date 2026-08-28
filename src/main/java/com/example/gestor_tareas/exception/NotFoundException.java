package com.example.gestor_tareas.exception;


public class NotFoundException extends RuntimeException{

	public NotFoundException(String mensaje) {
		super(mensaje);
	}
}
