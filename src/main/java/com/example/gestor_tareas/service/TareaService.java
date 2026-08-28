package com.example.gestor_tareas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gestor_tareas.domain.Tarea;
import com.example.gestor_tareas.domain.Usuario;
import com.example.gestor_tareas.exception.TareaNotFoundException;
import com.example.gestor_tareas.repository.TareaRepository;
import com.example.gestor_tareas.repository.UsuarioRepository;

@Service
public class TareaService {
	private final TareaRepository tareaRepository;
	private final UsuarioRepository usuarioRepository;
		
	public TareaService(TareaRepository tareaRepository, UsuarioRepository usuarioRepository) {
		this.tareaRepository = tareaRepository;
		this.usuarioRepository = usuarioRepository;
	}

	//crear tarea
	public Tarea crearTarea(Tarea tarea) {
		
		if(tarea == null) {
			return null;
		}
		
		if(tarea.getTitulo() == null || tarea.getTitulo().isBlank()) {
			return null;
		}
		
		if(tarea.getDescripcion() == null || tarea.getDescripcion().isBlank()){
			return null;
		}
		
		if(tarea.getUsuario() == null) {
			return null;
		}
		
		long idUsuario = tarea.getUsuario().getId();
		
		Optional<Usuario> usuarioBuscar = usuarioRepository.findById(idUsuario);
		
		if(usuarioBuscar.isEmpty()) {
			return null;
		}
		
		tarea.setUsuario(usuarioBuscar.get());
		
		
		return tareaRepository.save(tarea);
	}
	
	//mostrar todos por id de usuario
	public List<Tarea> mostrarTareas(Long usuarioId){
		
		return tareaRepository.findByUsuarioId(usuarioId);
		
	}
	
	//buscar una Tarea por id
	public Tarea buscarTareaPorId(Long id){
		
		Optional<Tarea> tarea = tareaRepository.findById(id);
		
		if(tarea.isPresent()) {
			return tarea.get();
		}
		
		throw new TareaNotFoundException("Tarea no encontrada");
	}
	
	
	//actuzalizar tarea
	public Tarea actualizarTarea(Long id, String titulo, String descripcion, boolean estado){
		
		Tarea tarea = buscarTareaPorId(id);
		
		if(titulo == null || titulo.isBlank()) {
			return null;
		}
		
		if(descripcion == null || descripcion.isBlank()) {
			return null;
		}

		
		tarea.setTitulo(titulo);
		tarea.setDescripcion(descripcion);
		tarea.setEstado(estado);
		
		
		
		return tareaRepository.save(tarea);
	}
	
	//Eliminar tarea
	public boolean eliminarTarea(Long id) {
		
		if(id == null) {
			return false;
		}
		
		buscarTareaPorId(id);
		
		
		tareaRepository.deleteById(id);;
		
		return true;
		
	}
	
}
