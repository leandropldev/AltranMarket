package pt.com.altran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pt.com.altran.dto.UsuarioDTO;
import pt.com.altran.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@Api(value = "Users EndPoint", description = "REST API for Users", tags = { "UserEndpoint" })	
public class UsuariosController {

	@Autowired
	private UsuarioService service;
	
	@ApiOperation(value = "Create User") 
	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO item) {
		return new ResponseEntity<>(service.create(item), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Find All Users")
	@GetMapping
	public List<UsuarioDTO> findAll(){
		return service.findAll();
	}
	
	@ApiOperation(value = "Find User by Id")
	@GetMapping("/{id}")
	public UsuarioDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@ApiOperation(value = "Update User")
	@PutMapping
	public UsuarioDTO update(@RequestBody UsuarioDTO item) {
		return service.update(item);
	}
	
	@ApiOperation(value = "Delete User")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
