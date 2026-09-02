package com.example.gestor_tareas.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.gestor_tareas.domain.Usuario;
import com.example.gestor_tareas.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final UsuarioRepository usuarioRepository;
	
	
	public JwtAuthenticationFilter(JwtService jwtService, UsuarioRepository usuarioRepository) {
		this.jwtService = jwtService;
		this.usuarioRepository = usuarioRepository;
	}
	


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			String authHeader = request.getHeader("Authorization");
	    
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        filterChain.doFilter(request, response);
	        return;
	    }
	    
	    String token = authHeader.substring(7);
	    
	   
	    
	    try {
	    	
	   
	    String email = jwtService.extractUserNAME(token);
	    
	    Usuario usuario = usuarioRepository.findByEmail(email)
	            .orElse(null);
	    
	    if (usuario != null && jwtService.isTokenValid(token, usuario) 
	            && SecurityContextHolder.getContext().getAuthentication() == null) {
	        
	        UsernamePasswordAuthenticationToken authentication = 
	                new UsernamePasswordAuthenticationToken(
	                        usuario,
	                        null,
	                        null);
	        
	        SecurityContextHolder.getContext()
	                .setAuthentication(authentication);
	      }
	    
	    }catch (Exception e) {
	        response.sendError(
	                HttpServletResponse.SC_UNAUTHORIZED,
	                "Token inválido o expirado"
	        );
	        return;
	    }
	    
	    filterChain.doFilter(request, response);
	}
	
	
}
