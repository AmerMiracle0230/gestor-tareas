package com.example.gestor_tareas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestor_tareas.domain.Usuario;
import com.example.gestor_tareas.dto.usuario.UsuarioCreateDTO;
import com.example.gestor_tareas.dto.usuario.UsuarioResponseDTO;
import com.example.gestor_tareas.dto.usuario.UsuarioUpdateDTO;
import com.example.gestor_tareas.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	//Endpoint para crear usuario y devolver un DTO como respuesta *****
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody UsuarioCreateDTO usuarioDTO) {
		
		UsuarioResponseDTO usuarioCreado = usuarioService.crearUsuario(usuarioDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
	}
	
	
	//Endpoint mostrar todos los usuarios ******
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> MostrarTodosLosUsuarios(){
		
		List<UsuarioResponseDTO> usuarios = usuarioService.buscarTodosLosUsuarios();
		
		return ResponseEntity.ok(usuarios);
		
	}
	
	//Endpoint para buscar un usuario *******
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id){
		
		UsuarioResponseDTO usuario =  usuarioService.buscarPorId(id);

		return ResponseEntity.ok(usuario);
		
		
	}
	
	//Endpoint para actualizar usuario  ******
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@PathVariable Long id,@Valid  @RequestBody UsuarioUpdateDTO usuarioDTO) {	
		
		UsuarioResponseDTO usuario = usuarioService.actualizarUsuario(id, usuarioDTO);
		
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
		
	}
	
	
	//Endpoint para eliminar usuario *****
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
		
		usuarioService.eliminarUsuario(id);	
		
		return ResponseEntity.noContent().build();
	}
	
}
