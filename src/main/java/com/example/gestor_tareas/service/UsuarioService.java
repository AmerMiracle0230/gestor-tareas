package com.example.gestor_tareas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gestor_tareas.domain.Usuario;
import com.example.gestor_tareas.dto.usuario.UsuarioCreateDTO;
import com.example.gestor_tareas.dto.usuario.UsuarioPatchDTO;
import com.example.gestor_tareas.dto.usuario.UsuarioResponseDTO;
import com.example.gestor_tareas.dto.usuario.UsuarioUpdateDTO;
import com.example.gestor_tareas.exception.EmailAlreadyExistsException;
import com.example.gestor_tareas.exception.UsuarioNotFoundException;
import com.example.gestor_tareas.mapper.UsuarioMapper;
import com.example.gestor_tareas.repository.UsuarioRepository;

// metodos de usuario : crear - buscarPorId- buscarTodos - actualizar - eliminar

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;

	
	
	public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioMapper = usuarioMapper;
		
	}

	//crear nuevo usuario  *****
	public UsuarioResponseDTO crearUsuario(UsuarioCreateDTO usuarioDTO) {
		
		Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
		
		
		if(usuarioRepository.existsByEmail(usuario.getEmail())) {
			throw new EmailAlreadyExistsException("El email ya esta registrado");
		}
		
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		
		return usuarioMapper.toResponseDTO(usuarioGuardado);
		
	}
	
	
	//buscar todos los usarios *******
	
	public List<UsuarioResponseDTO> buscarTodosLosUsuarios(){
		
		List<Usuario> usuarios = usuarioRepository.findAll(); 
		
		List<UsuarioResponseDTO> usuariosDTO = new ArrayList<>();
		
		for(Usuario u : usuarios) {
			usuariosDTO.add(usuarioMapper.toResponseDTO(u));
		}
		
		return usuariosDTO;
	}
	
	//buscar usarui por id *******
	
	public UsuarioResponseDTO buscarPorId(Long id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isPresent()){
			return usuarioMapper.toResponseDTO(usuario.get());
		}
		
		throw new UsuarioNotFoundException("Usuario no encontrado");
	}
	
	//actualizar usuario obteniendo datos de DTO (actualiza todo) ******
	
	public UsuarioResponseDTO actualizarUsuario(long id, UsuarioUpdateDTO usuarioDTO) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isEmpty()){
			throw new UsuarioNotFoundException("Usuario no encontrado");
		}

		Optional<Usuario> usuarioEmail = usuarioRepository.findByEmail(usuarioDTO.getEmail());
		
		if(usuarioEmail.isPresent() && !usuarioEmail.get().getId().equals(usuario.get().getId())) {
			throw new EmailAlreadyExistsException("El email ya esta registrado");
		}
		
		usuarioMapper.updateEntity(usuarioDTO, usuario.get());
		
		Usuario usuarioGuardar = usuarioRepository.save(usuario.get());
		
		return usuarioMapper.toResponseDTO(usuarioGuardar);
		
	}
	
	//actualiza parcialmente 
	public UsuarioResponseDTO actualizarParcialUsuario(long id, UsuarioPatchDTO usuarioDTO) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isEmpty()){
			throw new UsuarioNotFoundException("Usuario no encontrado");
		}
		

		if( usuarioDTO.getEmail() != null) {
			
			  Optional<Usuario> usuarioEmail = usuarioRepository.findByEmail(usuarioDTO.getEmail());

		        if(usuarioEmail.isPresent() && !usuarioEmail.get().getId().equals(usuario.get().getId())) {

		            throw new EmailAlreadyExistsException("El email ya esta registrado");
		        }
		}
		
		usuarioMapper.updatePartialEntity(usuarioDTO, usuario.get());
		
		Usuario usuarioGuardar = usuarioRepository.save(usuario.get());
		
		return usuarioMapper.toResponseDTO(usuarioGuardar);
		
	}
	
	//eliminar usuario *****
	public void eliminarUsuario(Long id) {
		
		
		if(!usuarioRepository.existsById(id)) {
			throw new UsuarioNotFoundException("Usuario no encontrado");
		}
		
		usuarioRepository.deleteById(id);
		
	}
	
  
	
}
