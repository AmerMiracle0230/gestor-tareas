package com.example.gestor_tareas.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gestor_tareas.domain.Usuario;
import com.example.gestor_tareas.dto.auth.LoginRequestDTO;
import com.example.gestor_tareas.exception.InvalidCredentialsException;
import com.example.gestor_tareas.exception.UsuarioNotFoundException;
import com.example.gestor_tareas.repository.UsuarioRepository;
import com.example.gestor_tareas.security.JwtService;

@Service
public class AuthService {
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	
	public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}


	public String login(LoginRequestDTO loginDTO) {
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(loginDTO.getEmail());
		
		if(usuario.isEmpty()) {
			throw new UsuarioNotFoundException("Usuario no encontrado");
		}
		
		if(!passwordEncoder.matches(loginDTO.getPassword(), usuario.get().getPassword())){
			throw new InvalidCredentialsException("Email o contrasaeña incorrectos");
		}
		
		return jwtService.generarToken(usuario.get());
	}
	
	
}
