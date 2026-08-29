package com.example.gestor_tareas.dto.tarea;

public class TareaPatchDTO {
	
	 	private String titulo;

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
