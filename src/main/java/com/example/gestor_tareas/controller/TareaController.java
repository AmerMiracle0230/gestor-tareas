package com.example.gestor_tareas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestor_tareas.domain.Tarea;
import com.example.gestor_tareas.service.TareaService;

@RestController
@RequestMapping("/tareas")
public class TareaController {
	private final TareaService tareaService;

	public TareaController(TareaService tareaService) {
		this.tareaService = tareaService;
	}
	
	//Endpoint crea tarea
	@PostMapping
	public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
		
		Tarea tareaCreada = tareaService.crearTarea(tarea);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tareaCreada);
		
	}
	
	//Endpoint buscar todas las tareas de un usurio
	@GetMapping
	public ResponseEntity<List<Tarea>> buscarTareas(@RequestParam Long usuarioId){
		
		List<Tarea> tareas = tareaService.mostrarTareas(usuarioId);
		
		return ResponseEntity.ok(tareas);
	}
	
	//Endpoint buscar una tarea por id 
	@GetMapping("/{id}")
	public ResponseEntity<Tarea> buscarTareaPorId(@PathVariable Long id){
		
		Tarea tarea = tareaService.buscarTareaPorId(id);

		return ResponseEntity.ok(tarea);
	}
	
	//Endpoint actualizar una tarea
	@PutMapping("/{id}")
	public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
		
		Tarea tareaActualizar = tareaService.actualizarTarea(
				id, 
				tarea.getTitulo(), 
				tarea.getDescripcion(), 
				tarea.isEstado());
		
		if(tareaActualizar == null) {
			return ResponseEntity.notFound().build();
		}
		
		
		return ResponseEntity.ok(tareaActualizar);
	}
	
	//Endpoint eliminar una tarea
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
		
		boolean eliminar = tareaService.eliminarTarea(id);
		
		if(!eliminar) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
}
