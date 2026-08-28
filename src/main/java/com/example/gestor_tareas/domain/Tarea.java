package com.example.gestor_tareas.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    private String titulo;

	@NotBlank
	@Size(max= 100)
    private String descripcion;

    private boolean estado;
    
    @ManyToOne
    @NotNull
    private Usuario usuario;

    public Tarea() {
        super();
    }

    public Tarea(String titulo, String descripcion, boolean estado, Usuario usuario) {
        super();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuario = usuario;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    
    
    
    
}