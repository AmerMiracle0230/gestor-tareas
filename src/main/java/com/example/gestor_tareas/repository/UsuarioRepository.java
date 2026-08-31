package com.example.gestor_tareas.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestor_tareas.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	boolean existsByEmail(String email);
	Optional<Usuario> findByEmail(String email);
}
