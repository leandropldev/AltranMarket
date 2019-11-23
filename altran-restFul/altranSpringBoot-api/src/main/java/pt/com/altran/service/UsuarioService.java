package pt.com.altran.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.com.altran.converter.DozerConverter;
import pt.com.altran.dto.UsuarioDTO;
import pt.com.altran.entity.UsuarioEntiy;
import pt.com.altran.exceptions.ResourceException;
import pt.com.altran.mongoSequences.NextCustomSequenceService;
import pt.com.altran.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	private NextCustomSequenceService nextSequenceService;
	
	public UsuarioDTO create(UsuarioDTO dto) {
		UsuarioEntiy entity = DozerConverter.parseObject(dto, UsuarioEntiy.class);
		
		if(repository.findUsuarioEntiyByNomeOrEmail(entity.getNome(), entity.getEmail()).isPresent()) 
			throw new ResourceException("Já existe um usuario cadastrado com o este nome e/ou email: " + dto.getNome() + "/" + dto.getEmail());
		entity.setId(nextSequenceService.getNextSequence("usuarioSequence"));
			
		return DozerConverter.parseObject(repository.save(entity), UsuarioDTO.class);
	}
	
	public List<UsuarioDTO> findAll(){
		return DozerConverter.parseListObjects(repository.findAll(), UsuarioDTO.class); 
	}
	
	public UsuarioDTO findById(long id) {
		UsuarioEntiy entity = repository.findById(id)
			.orElseThrow(() -> new ResourceException("Nenhum Usuario encontrado com o id " + id));
		
		return DozerConverter.parseObject(repository.save(entity), UsuarioDTO.class);
	}
	
	public UsuarioDTO update(UsuarioDTO dto) {
		repository.findById(dto.getId())
				.orElseThrow(() -> new ResourceException("Nenhum Usuario encontrado com o id " + dto.getId()));
		
		UsuarioEntiy entity = UsuarioEntiy.builder()
			.id(dto.getId())
			.nome(dto.getNome())
			.email(dto.getEmail())
			.build();
		
		return DozerConverter.parseObject(repository.save(entity), UsuarioDTO.class);
	}
	
	public void delete(Long id) {
		UsuarioEntiy entity = repository.findById(id)
				.orElseThrow(() -> new ResourceException("Nenhum Usuario encontrado com o id " + id));
		repository.delete(entity);
	}
}
