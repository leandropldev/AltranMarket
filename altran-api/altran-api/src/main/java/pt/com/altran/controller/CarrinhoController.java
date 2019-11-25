package pt.com.altran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
@Api(value = "Carrinho EndPoint", description = "REST API for Carrinho", tags = { "CarrinhoEndpoint" })
public class CarrinhoController {

	@Autowired
	private CarrinhoService service;
	
	@ApiOperation(value = "Create Cart")
	@PostMapping
	public ResponseEntity<CarrinhoDTO> create(@RequestBody CarrinhoDTO carrinho) {
		return new ResponseEntity<>(service.create(carrinho), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "List Cart")
	@GetMapping
	public List<CarrinhoDTO> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "List Cart by Id")
	@GetMapping("/{id}")
	public CarrinhoDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

}
