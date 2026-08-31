package com.example.gestor_tareas.dto.tarea;

import jakarta.validation.constraints.*;

public class TareaPatchDTO {
	
		@Size(min = 6, max = 100, message = "El título debe tener entre 6 y 100 caracteres")
	 	private String titulo;

		@Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
	    private String descripcion;

	    private Boolean estado;

	    private Long usuarioId;

	    public TareaPatchDTO() {
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

	    
	    public Boolean getEstado() {
			return estado;
		}

		public void setEstado(Boolean estado) {
			this.estado = estado;
		}

		public Long getUsuarioId() {
	        return usuarioId;
	    }

	    public void setUsuarioId(Long usuarioId) {
	        this.usuarioId = usuarioId;
	    }
}
