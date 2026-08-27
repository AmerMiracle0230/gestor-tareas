package com.example.gestor_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestor_tareas.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
