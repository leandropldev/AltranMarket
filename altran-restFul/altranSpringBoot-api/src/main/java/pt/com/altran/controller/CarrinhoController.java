package pt.com.altran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoService service;
	
	@PostMapping
	public CarrinhoDTO create(@RequestBody CarrinhoDTO carrinho) {
		return service.createItem(carrinho);
	}
	
	@GetMapping
	public List<CarrinhoDTO> findAll(){
		return service.findAll();
	}

	@GetMapping("/{id}")
	public CarrinhoDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

}
