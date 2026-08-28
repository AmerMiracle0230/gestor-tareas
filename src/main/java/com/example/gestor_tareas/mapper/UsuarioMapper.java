package com.example.gestor_tareas.mapper;

import org.springframework.stereotype.Component;

import com.example.gestor_tareas.domain.Usuario;
import com.example.gestor_tareas.dto.usuario.UsuarioCreateDTO;
import com.example.gestor_tareas.dto.usuario.UsuarioResponseDTO;
import com.example.gestor_tareas.dto.usuario.UsuarioUpdateDTO;

@Component
public class UsuarioMapper {
	
	//Convertidor DTO para Usuario
	
	//Convertidor Usuario a DTO (al crear)
	public Usuario toEntity(UsuarioCreateDTO usuarioDTO) {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setPassword(usuarioDTO.getPassword());
		
		return usuario;
	}	
	
	//Convertidor DTO a Usuario (al responder)
	public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
		
		return new UsuarioResponseDTO(
				usuario.getId(),
				usuario.getNombre(),
				usuario.getEmail());
	}
	
	// Actualiza una entidad Usuario existente con los datos del DTO
	public void updateEntity(UsuarioUpdateDTO usuarioDTO, Usuario usuario) {
		
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setPassword(usuarioDTO.getPassword());
		
	}
	
}
