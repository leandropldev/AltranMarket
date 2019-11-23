package pt.com.altran.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.altran.converter.DozerConverter;
import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.entity.CarrinhoEntity;
import pt.com.altran.exceptions.ResourceException;
import pt.com.altran.mongoSequences.NextCustomSequenceService;
import pt.com.altran.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
	
	@Autowired
	private NextCustomSequenceService nextSequenceService;
	
	@Autowired
	private CarrinhoRepository repository;
	
	public CarrinhoDTO createItem(CarrinhoDTO dto) {
		CarrinhoEntity entity = DozerConverter.parseObject(dto, CarrinhoEntity.class);
		
		if(repository.findCarrinhoEntityById(dto.getId()).isPresent()) 
			throw new ResourceException("Já existe uma compra cadastrado com este id: " + dto.getId());
		
		entity.setId(nextSequenceService.getNextSequence("carrinhoSequence"));
		
		return DozerConverter.parseObject(repository.save(entity), CarrinhoDTO.class);
	}
	
	public List<CarrinhoDTO> findAll(){
		return DozerConverter.parseListObjects(repository.findAll(), CarrinhoDTO.class); 
	}
	
	public CarrinhoDTO findById(long id) {
		CarrinhoEntity entity = repository.findById(id)
			.orElseThrow(() -> new ResourceException("Nenhuma compra encontrada com o id " + id));
		
		return DozerConverter.parseObject(repository.save(entity), CarrinhoDTO.class);
	}

}
