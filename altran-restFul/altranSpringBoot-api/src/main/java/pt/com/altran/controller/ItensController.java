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

import pt.com.altran.dto.ItemDTO;
import pt.com.altran.service.ItensService;

@RestController
@RequestMapping("/itens")
public class ItensController {

	@Autowired
	private ItensService service;
	
	@PostMapping
	public ItemDTO create(@RequestBody ItemDTO item) {
		return service.createItem(item);
	}
	
	@GetMapping
	public List<ItemDTO> findAll(){
		return service.getItens();
	}
	
	@GetMapping("/{id}")
	public ItemDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PutMapping
	public ItemDTO update(@RequestBody ItemDTO item) {
		return service.update(item);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
