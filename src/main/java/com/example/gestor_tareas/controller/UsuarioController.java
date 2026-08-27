package com.example.gestor_tareas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestor_tareas.domain.Usuario;
import com.example.gestor_tareas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	//Endpoint para crear usuario
	@PostMapping
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
		
		Usuario usuarioCreado = usuarioService.crearUsuario(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
	}
	
	//Endpoint mostrar todos los usuarios
	@GetMapping
	public ResponseEntity<List<Usuario>> MostrarTodosLosUsuarios(){
		
		List<Usuario> usuarios = usuarioService.buscarTodosLosUsuarios();
		
		return ResponseEntity.ok(usuarios);
		
	}
	
	
	
	//Endpoint para buscar un usuario
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id){
		
		Optional<Usuario> usuario =  usuarioService.buscarPorId(id);
		
		if(usuario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
	
		return ResponseEntity.ok(usuario.get());
		
	}
	
	
	
	//Endpoint para actualizar usuario
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {	
		
		Usuario usuarioActualizar = usuarioService.actualizarUsuario(
				id, 
				usuario.getNombre(), 
				usuario.getEmail(), 
				usuario.getPassword());
		
		if(usuarioActualizar == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuarioActualizar);
		
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
		
		boolean eliminado = usuarioService.eliminarUsuario(id);
		
		if(!eliminado) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
}
