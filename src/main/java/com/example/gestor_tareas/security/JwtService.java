package com.example.gestor_tareas.security; 
import java.util.Date; 
import javax.crypto.SecretKey; 
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Service; 
import com.example.gestor_tareas.domain.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts; 
import io.jsonwebtoken.security.Keys; 

@Service 
public class JwtService { 
	
	@Value("${jwt.secret}") 
	private String secret; 
	
	// GENERAR TOKEN
	public String generarToken(Usuario usuario) { 
		
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes()); 
		
		return Jwts.builder()
				.subject(usuario.getEmail()) 
				.issuedAt(new Date()) 
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
				.signWith(key) .compact(); 
		
		} 
	// EXTRAER EMAIL DEL TOKEN
	public String extractUserNAME(String token) {
		
		
		  Claims claims = extractAllClaims(token);
		
		return claims.getSubject();
	}
	
	// COMPROBAR SI EL TOKEN ES VÁLIDO
	public boolean isTokenValid(String token , Usuario usuario) {
		
		String email = extractUserNAME(token);
		
		return email.equals(usuario.getEmail()) && !isTokenExpired(token);
	}
	
	// COMPROBAR SI EL TOKEN HA CADUCADO
	public boolean isTokenExpired(String token) {
		
		  Claims claims = extractAllClaims(token);
		
		return claims.getExpiration().before(new Date());
	}
	
	//OBTENER LA INFORMACIÓN DEL TOKEN
	private Claims extractAllClaims(String token) {

	    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

	    return Jwts.parser()
	            .verifyWith(key)
	            .build()
	            .parseSignedClaims(token)
	            .getPayload();
	}

}