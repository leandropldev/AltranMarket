package pt.com.altran.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pt.com.altran.dto.UsuarioDTO;
import pt.com.altran.entity.UsuarioEntiy;
import pt.com.altran.exceptions.RequestException;
import pt.com.altran.mocks.MockUsuario;
import pt.com.altran.mongoSequences.NextCustomSequenceService;
import pt.com.altran.repository.UsuarioRepository;

public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService service;
	
	@Mock
	private UsuarioRepository repository;
	
	@Mock
	private NextCustomSequenceService sequence;
	
	private MockUsuario inputObject;
	
	@Before
	public void init() {
		inputObject = new MockUsuario();
		MockitoAnnotations.initMocks(this);
		Mockito.when(sequence.getNextSequence("teste")).thenReturn(1);
	}
	
	@Test
	public void create() throws Exception {
		UsuarioEntiy mockUsuarioEntiy = inputObject.mockUsuarioEntiy();
		
		Mockito.when(repository.findUsuarioEntiyByNomeOrEmail(mockUsuarioEntiy.getNome(), mockUsuarioEntiy.getEmail())).thenReturn(null);
		Mockito.when(repository.save(mockUsuarioEntiy)).thenReturn(mockUsuarioEntiy);
		
		UsuarioDTO create = service.create(inputObject.mockUsuarioDTO());
		
		assertEquals("Mock Nome 0", create.getNome());
		assertEquals("Mock email 0", create.getEmail());
	}
	
	@Test(expected = RequestException.class)
	public void create_RequestException() throws Exception {
		UsuarioEntiy mockUsuarioEntiy = inputObject.mockUsuarioEntiy();
		
		Mockito.when(repository.findUsuarioEntiyByNomeOrEmail(mockUsuarioEntiy.getNome(), mockUsuarioEntiy.getEmail())).thenReturn(mockUsuarioEntiy);
		Mockito.when(repository.save(mockUsuarioEntiy)).thenReturn(mockUsuarioEntiy);
		
		service.create(inputObject.mockUsuarioDTO());
	}
	
	@Test
	public void findAll() {
		Mockito.when(repository.findAll()).thenReturn(inputObject.mockListUsuarioEntity());
		List<UsuarioDTO> findAll = service.findAll();
		
		assertEquals(11, findAll.size());
	}
	
	@Test
	public void findById() {
		UsuarioEntiy mockUsuarioEntiy = inputObject.mockUsuarioEntiy();
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(mockUsuarioEntiy));
		Mockito.when(repository.save(mockUsuarioEntiy)).thenReturn(mockUsuarioEntiy);
		UsuarioDTO find = service.findById(1L);
		
		assertEquals(0L, find.getId());
	}
	
	@Test
	public void update() {
		UsuarioEntiy mockUsuarioEntiy = inputObject.mockUsuarioEntiy();
		
		Mockito.when(repository.findById(mockUsuarioEntiy.getId())).thenReturn(Optional.of(mockUsuarioEntiy));
		Mockito.when(repository.save(mockUsuarioEntiy)).thenReturn(mockUsuarioEntiy);
		
		UsuarioDTO create = service.update(inputObject.mockUsuarioDTO());
		assertEquals("Mock Nome 0", create.getNome());
	}
	
	@Test
	public void delete() {
		UsuarioEntiy mockUsuarioEntiy = inputObject.mockUsuarioEntiy();;
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(mockUsuarioEntiy));
		
		doNothing().when(repository).delete(mockUsuarioEntiy);
	}
}
