package com.example.gestor_tareas.dto.usuario;

import jakarta.validation.constraints.*;

public class UsuarioPatchDTO {

	@Size(min = 3 , max=100, message="El nombre debe tener entre 3 y 100 caracteres")
	private String nombre;
	
	@Email(message="El email no tiene un formato valido")
	private String email;
	
	@Size(min= 10,max =100, message="La contraseña debe tener entre 10 y 100 caracateres")
	private String password;

	
	public UsuarioPatchDTO() {
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
