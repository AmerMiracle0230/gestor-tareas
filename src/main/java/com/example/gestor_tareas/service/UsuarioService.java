package com.example.gestor_tareas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gestor_tareas.domain.Usuario;
import com.example.gestor_tareas.repository.UsuarioRepository;

// metodos de usuario : crear - buscarPorId- buscarTodos - actualizar - eliminar

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	//crear nuevo usuario con validaciones basicas
	public Usuario crearUsuario(Usuario usuario) {
		
		//ver si es null
		if(usuario == null) {	
			return null;			
		}
		
		//ver si el nombre esta vacio o es null
		if(usuario.getNombre() == null || usuario.getNombre().isBlank() ) {
			return null;
		}
		
		//ver si el email es null o esta vacio
		if(usuario.getEmail() == null || usuario.getEmail().isBlank()){
			return null;
		}
		
		//ver si el contraseña es null o esta vacio
		if(usuario.getPassword() == null || usuario.getPassword().isBlank()) {
			return null;
		}
		
		//guardar y devolver usuario
		return usuarioRepository.save(usuario);
	}
	
	
	//buscar todos los usarios
	
	public List<Usuario> buscarTodosLosUsuarios(){
		
		return usuarioRepository.findAll();
	}
	
	//buscar usarui por id
	
	public Optional<Usuario> buscarPorId(Long id) {
		
		if(id == null){
			return Optional.empty();
		}
		
		return usuarioRepository.findById(id);
	}
	
	//actualizar usuario
	
	public Usuario actualizarUsuario(long id, String nombre, String email, String password) {
		
		Optional<Usuario> buscarUsuario = buscarPorId(id);
		
		Usuario usuario;
		
		if(buscarUsuario.isEmpty()) {
			return null;
		}
		
		if(nombre == null || nombre.isBlank()) {
			return null;
		}
		
		if(email == null || email.isBlank()) {
			return null;
		}
		
		if(password == null || password.isBlank()) {
			return null;
		}
		
		usuario = buscarUsuario.get();
		
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setPassword(password);
		
		return usuarioRepository.save(usuario);
		
	}
	
	//eliminar usuario
	public boolean eliminarUsuario(Long id) {
		
		if(id == null) {
			return false;
		}
		
		Optional<Usuario> usuarioEliminar = buscarPorId(id);
		
		if(usuarioEliminar.isEmpty()) {
			return false;
		}
		
		usuarioRepository.deleteById(id);
		
		return true;
	}
	
  
	
}
