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
import pt.com.altran.dto.ItemDTO;
import pt.com.altran.service.ItensService;

@RestController
@RequestMapping("/itens")
@Api(value = "Item EndPoint", description = "REST API for Item", tags = { "ItemEndpoint" })
public class ItensController {

	@Autowired
	private ItensService service;
	
	@ApiOperation(value = "Create Item")
	@PostMapping
	public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO item) {
		return new ResponseEntity<>(service.create(item), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Find All Items")
	@GetMapping
	public List<ItemDTO> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Find Item by Id")
	@GetMapping("/{id}")
	public ItemDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@ApiOperation(value = "Update Item")
	@PutMapping
	public ItemDTO update(@RequestBody ItemDTO item) {
		return service.update(item);
	}
	
	@ApiOperation(value = "Delete Item")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
