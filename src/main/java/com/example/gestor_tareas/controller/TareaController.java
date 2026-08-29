package com.example.gestor_tareas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestor_tareas.dto.tarea.TareaCreateDTO;
import com.example.gestor_tareas.dto.tarea.TareaPatchDTO;
import com.example.gestor_tareas.dto.tarea.TareaResponseDTO;
import com.example.gestor_tareas.dto.tarea.TareaUpdateDTO;
import com.example.gestor_tareas.service.TareaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tareas")
public class TareaController {
	private final TareaService tareaService;

	public TareaController(TareaService tareaService) {
		this.tareaService = tareaService;
	}
	
	//Endpoint crea tarea
	@PostMapping
	public ResponseEntity<TareaResponseDTO> crearTarea(@Valid @RequestBody TareaCreateDTO tareaDTO) {
		
		TareaResponseDTO tareaCreada = tareaService.crearTarea(tareaDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tareaCreada);
		
	}
	
	//Endpoint buscar todas las tareas de un usurio
	@GetMapping
	public ResponseEntity<List<TareaResponseDTO>> buscarTareas(@RequestParam Long usuarioId){
		
		List<TareaResponseDTO> tareas = tareaService.mostrarTareas(usuarioId);
		
		return ResponseEntity.ok(tareas);
	}
	
	//Endpoint buscar una tarea por id 
	@GetMapping("/{id}")
	public ResponseEntity<TareaResponseDTO> buscarTareaPorId(@PathVariable Long id){
		
		TareaResponseDTO tarea = tareaService.buscarTareaPorId(id);

		return ResponseEntity.ok(tarea);
	}
	
	//Endpoint actualizar una tarea
	@PutMapping("/{id}")
	public ResponseEntity<TareaResponseDTO> actualizarTarea(@PathVariable Long id,@Valid @RequestBody TareaUpdateDTO tareaDTO) {
		
		TareaResponseDTO tareaActualizar = tareaService.actualizarTarea(id, tareaDTO);
	

		return ResponseEntity.ok(tareaActualizar);
	}
	
	//Endpoint actualizar parcial una tarea
	@PatchMapping("/{id}")
	public ResponseEntity<TareaResponseDTO> actualizarParcialTarea(@PathVariable Long id,@RequestBody TareaPatchDTO tareaDTO) {
			
			TareaResponseDTO tareaActualizar = tareaService.actualizarParcialTarea(id, tareaDTO);

			return ResponseEntity.ok(tareaActualizar);
	}
		
	
	//Endpoint eliminar una tarea
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
		
		tareaService.eliminarTarea(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
