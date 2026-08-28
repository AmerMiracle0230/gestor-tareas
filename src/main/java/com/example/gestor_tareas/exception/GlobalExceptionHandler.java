package com.example.gestor_tareas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	//Exception para ERROR  404 
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> notFound(NotFoundException ex){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		
	}
	
	//Exception para ERROR 400 , detecta errores pero solo muestra 1 (revisar)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> validationError(MethodArgumentNotValidException ex){
		
		String mensaje = ex.getBindingResult().getFieldError().getDefaultMessage();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
		
	}
	
}
