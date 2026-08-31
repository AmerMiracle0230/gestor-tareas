package com.example.gestor_tareas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestor_tareas.dto.auth.LoginRequestDTO;
import com.example.gestor_tareas.dto.auth.LoginResponseDTO;
import com.example.gestor_tareas.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginDTO) {
		
		String token = authService.login(loginDTO);
		
		LoginResponseDTO response = new LoginResponseDTO(token);
		
		return ResponseEntity.ok(response);
	}
	
	
}
