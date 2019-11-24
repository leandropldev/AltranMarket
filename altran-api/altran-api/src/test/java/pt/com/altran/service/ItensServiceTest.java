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

import pt.com.altran.dto.ItemDTO;
import pt.com.altran.entity.ItemEntity;
import pt.com.altran.exceptions.RequestException;
import pt.com.altran.mocks.MockItem;
import pt.com.altran.mongoSequences.NextCustomSequenceService;
import pt.com.altran.repository.ItensRepository;

public class ItensServiceTest {

	@InjectMocks
	private ItensService service;
	
	@Mock
	private ItensRepository repository;
	
	@Mock
	private NextCustomSequenceService sequence;
	
	private MockItem inputObject;
	
	@Before
	public void init() {
		inputObject = new MockItem();
		MockitoAnnotations.initMocks(this);
		Mockito.when(sequence.getNextSequence("teste")).thenReturn(1);
	}
	
	@Test
	public void create() throws Exception {
		ItemEntity mockItemEntity = inputObject.mockItemEntity();
		
		Mockito.when(repository.findItemEntityByNome(mockItemEntity.getNome())).thenReturn(null);
		Mockito.when(repository.save(mockItemEntity)).thenReturn(mockItemEntity);
		
		ItemDTO create = service.create(inputObject.mockItemDTO());
		
		assertEquals("Mock Nome 0", create.getNome());
		assertEquals(10L, create.getValor(), 0);
	}
	
	@Test(expected = RequestException.class)
	public void create_RequestException() throws Exception {
		ItemEntity mockItemEntity = inputObject.mockItemEntity();
		
		Mockito.when(repository.findItemEntityByNome(mockItemEntity.getNome())).thenReturn(mockItemEntity);
		Mockito.when(repository.save(mockItemEntity)).thenReturn(mockItemEntity);
		
		service.create(inputObject.mockItemDTO());
	}
	
	@Test
	public void findAll() {
		Mockito.when(repository.findAll()).thenReturn(inputObject.mockListItemEntity());
		List<ItemDTO> findAll = service.findAll();
		
		assertEquals(11, findAll.size());
	}
	
	@Test
	public void findById() {
		ItemEntity mockItemEntity = inputObject.mockItemEntity();
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(mockItemEntity));
		Mockito.when(repository.save(mockItemEntity)).thenReturn(mockItemEntity);
		ItemDTO find = service.findById(1L);
		
		assertEquals(0L, find.getId());
	}
	
	@Test
	public void update() {
		ItemEntity mockItemEntity = inputObject.mockItemEntity();
		
		Mockito.when(repository.findById(mockItemEntity.getId())).thenReturn(Optional.of(mockItemEntity));
		Mockito.when(repository.save(mockItemEntity)).thenReturn(mockItemEntity);
		
		ItemDTO create = service.update(inputObject.mockItemDTO());
		assertEquals("Mock Nome 0", create.getNome());
		assertEquals(10L, create.getValor(), 0);
	}
	
	@Test
	public void delete() {
		ItemEntity mockItemEntity = inputObject.mockItemEntity();
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(mockItemEntity));
		
		doNothing().when(repository).delete(mockItemEntity);
	}
}
