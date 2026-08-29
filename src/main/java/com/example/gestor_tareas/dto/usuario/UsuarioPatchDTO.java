package com.example.gestor_tareas.dto.usuario;

import jakarta.validation.constraints.Email;

public class UsuarioPatchDTO {

	private String nombre;
	
	@Email
	private String email;
	
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
