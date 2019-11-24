package pt.com.altran.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.altran.converter.DozerConverter;
import pt.com.altran.dto.ItemDTO;
import pt.com.altran.entity.ItemEntity;
import pt.com.altran.exceptions.RequestException;
import pt.com.altran.exceptions.ResourceNotFoundException;
import pt.com.altran.mongoSequences.NextCustomSequenceService;
import pt.com.altran.repository.ItensRepository;

@Service
public class ItensService {
	
	@Autowired
	private NextCustomSequenceService nextSequenceService;
	
	@Autowired
	private ItensRepository repository;
	
	public ItemDTO create(ItemDTO dto) {
		ItemEntity entity = DozerConverter.parseObject(dto, ItemEntity.class);
		
		if(repository.findItemEntityByNome(entity.getNome()) != null) 
			throw new RequestException("Já existe um item cadastrado com o nomem: " + dto.getNome());
		
		entity.setId(nextSequenceService.getNextSequence("itemSequence"));
			
		return DozerConverter.parseObject(repository.save(entity), ItemDTO.class);
	}
	
	public List<ItemDTO> findAll(){
		return DozerConverter.parseListObjects(repository.findAll(), ItemDTO.class); 
	}
	
	public ItemDTO findById(long id) {
		ItemEntity entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Nenhum item encontrado com o id " + id));
		
		return DozerConverter.parseObject(repository.save(entity), ItemDTO.class);
	}
	
	public ItemDTO update(ItemDTO dto) {
		repository.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum item encontrado com o id " + dto.getId()));
		
		ItemEntity entity = ItemEntity.builder()
			.id(dto.getId())
			.nome(dto.getNome())
			.valor(dto.getValor())
			.build();
		
		return DozerConverter.parseObject(repository.save(entity), ItemDTO.class);
	}
	
	public void delete(Long id) {
		ItemEntity entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum item encontrado com o id " + id));
		repository.delete(entity);
	}
}
