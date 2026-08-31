package com.example.gestor_tareas.dto.tarea;

import jakarta.validation.constraints.*;


public class TareaUpdateDTO {
	
	@NotBlank(message="El titulo es obligatorio")
	@Size(min=6, max=100, message="El titulo debe tener entre 6 y 100 caracteres")
    private String titulo;

	@NotBlank(message="La descripcion es obligatorio")
    private String descripcion;

    private boolean estado;

		
	public TareaUpdateDTO() {
	}
	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
