package com.example.gestor_tareas.dto.tarea;

import jakarta.validation.constraints.*;

public class TareaCreateDTO {

	@NotBlank(message="El titulo es obligatorio")
	@Size(min=10, max=100)
    private String titulo;

	@NotBlank(message="La descripcion es obligatorio")
    private String descripcion;

    private boolean estado;

    @NotNull(message= "El usuario no puede ser null")
    private Long usuarioId;

    public TareaCreateDTO() {
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}