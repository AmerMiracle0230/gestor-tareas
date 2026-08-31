package com.example.gestor_tareas.dto.usuario;

import jakarta.validation.constraints.*;

public class UsuarioCreateDTO {
	
	@NotBlank(message= "El nombre es obligatorio")
	@Size(min=3, max=100, message = "el nombre debe tener minimo 3 caracteres")
	private String nombre;
	
	@NotBlank(message= "El email es obligatorio")
	@Email(message="El email no tiene un formato valido")
	@Size(max=100)
	private String email;
	
	@NotBlank(message="La contraseña es obligatoria")
	@Size(min=10, max=100,message="la contraseña debe tener entre 10 y 100 caracteres")
	private String password;

	
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
