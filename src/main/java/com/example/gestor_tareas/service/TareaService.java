package com.example.gestor_tareas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gestor_tareas.domain.Tarea;
import com.example.gestor_tareas.domain.Usuario;
import com.example.gestor_tareas.dto.tarea.TareaCreateDTO;
import com.example.gestor_tareas.dto.tarea.TareaPatchDTO;
import com.example.gestor_tareas.dto.tarea.TareaResponseDTO;
import com.example.gestor_tareas.dto.tarea.TareaUpdateDTO;
import com.example.gestor_tareas.exception.TareaNotFoundException;
import com.example.gestor_tareas.exception.UsuarioNotFoundException;
import com.example.gestor_tareas.mapper.TareaMapper;
import com.example.gestor_tareas.repository.TareaRepository;
import com.example.gestor_tareas.repository.UsuarioRepository;

@Service
public class TareaService {
	private final TareaRepository tareaRepository;
	private final UsuarioRepository usuarioRepository;
	private final TareaMapper tareaMapper;
	

	public TareaService(TareaRepository tareaRepository, UsuarioRepository usuarioRepository, TareaMapper tareaMapper) {
		this.tareaRepository = tareaRepository;
		this.usuarioRepository = usuarioRepository;
		this.tareaMapper = tareaMapper;
	}

	//crear tarea con DTO de respuesta ****
	public TareaResponseDTO crearTarea(TareaCreateDTO tareaDTO) {
		
		Tarea tarea = tareaMapper.toEntity(tareaDTO);
		
		Optional<Usuario> usuario =  usuarioRepository.findById(tareaDTO.getUsuarioId());
		
		if(usuario.isEmpty()) {
			throw new UsuarioNotFoundException("Usuario no encontrado");
		}
		
		tarea.setUsuario(usuario.get());
		
		Tarea tareaGuardado = tareaRepository.save(tarea);
		
		return tareaMapper.toResponseDTO(tareaGuardado);
	}
	 
	//mostrar todos por id de usuario *****
	public List<TareaResponseDTO> mostrarTareas(Long usuarioId){
		
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		
		if(usuario.isEmpty()) {
			throw new UsuarioNotFoundException("Usuario no encontrado");
		}
		
		List<Tarea> tareas =  tareaRepository.findByUsuarioId(usuarioId);
		
		
		List<TareaResponseDTO> tareasDTO = new ArrayList<>();
		
		for(Tarea t : tareas) {
			tareasDTO.add(tareaMapper.toResponseDTO(t));
		}
		
		return tareasDTO;
		
	}
	
	//buscar una Tarea por id
	public TareaResponseDTO buscarTareaPorId(Long id){
		
		Optional<Tarea> tarea = tareaRepository.findById(id);
		
		if(tarea.isPresent()) {
			return tareaMapper.toResponseDTO(tarea.get());
		}
		
		throw new TareaNotFoundException("Tarea no encontrada");
	}
	
	
	//actuzalizar tarea todos los campos
	public TareaResponseDTO actualizarTarea(Long id, TareaUpdateDTO tareaDTO){
		
		Optional<Tarea> tarea = tareaRepository.findById(id);

		if(tarea.isEmpty()) {
			throw new TareaNotFoundException("Tarea no encontrada");
		}
		
		tareaMapper.updateEntity(tareaDTO, tarea.get());
		
		Tarea tareaGuardar = tareaRepository.save(tarea.get());
		
		return tareaMapper.toResponseDTO(tareaGuardar);
	}
	
	//Actuzaliar parcialmente tareas
	public TareaResponseDTO actualizarParcialTarea(Long id, TareaPatchDTO tareaDTO){
		
		Optional<Tarea> tarea = tareaRepository.findById(id);

		if(tarea.isEmpty()) {
			throw new TareaNotFoundException("Tarea no encontrada");
		}
		
		tareaMapper.updateParcialEntity(tareaDTO, tarea.get());
		
		Tarea tareaGuardar = tareaRepository.save(tarea.get());
		
		return tareaMapper.toResponseDTO(tareaGuardar);
	}
	
	
	//Eliminar tarea
	public void eliminarTarea(Long id) {
		

		if(!tareaRepository.existsById(id)) {
			throw new TareaNotFoundException("Tarea no encontrada");
		}
	
		tareaRepository.deleteById(id);
			
	}
	
}
