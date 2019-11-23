package pt.com.altran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.com.altran.dto.UsuarioDTO;
import pt.com.altran.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public UsuarioDTO create(@RequestBody UsuarioDTO item) {
		return service.create(item);
	}
	
	@GetMapping
	public List<UsuarioDTO> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public UsuarioDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PutMapping
	public UsuarioDTO update(@RequestBody UsuarioDTO item) {
		return service.update(item);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
