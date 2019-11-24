package pt.com.altran.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.entity.CarrinhoEntity;
import pt.com.altran.exceptions.RequestException;
import pt.com.altran.mocks.MockCarrinho;
import pt.com.altran.mongoSequences.NextCustomSequenceService;
import pt.com.altran.repository.CarrinhoRepository;

public class CarrinhoServiceTest {

	@InjectMocks
	private CarrinhoService service;
	
	@Mock
	private CarrinhoRepository repository;
	
	@Mock
	private NextCustomSequenceService sequence;
	
	private MockCarrinho inputObject;
	
	@Before
	public void init() {
		inputObject = new MockCarrinho();
		MockitoAnnotations.initMocks(this);
		Mockito.when(sequence.getNextSequence("teste")).thenReturn(1);
	}
	
	@Test
	public void create() throws Exception {
		CarrinhoEntity mockCarrinhoEntity = inputObject.mockCarrinhoEntity();
		
		Mockito.when(repository.findCarrinhoEntityById(mockCarrinhoEntity.getId())).thenReturn(null);
		Mockito.when(repository.save(mockCarrinhoEntity)).thenReturn(mockCarrinhoEntity);
		
		CarrinhoDTO create = service.create(inputObject.mockCarrinhoDTO());
		
		assertEquals(0, create.getId());
	}
	
	@Test(expected = RequestException.class)
	public void create_RequestException() throws Exception {
		CarrinhoEntity mockCarrinhoEntity = inputObject.mockCarrinhoEntity();
		
		Mockito.when(repository.findCarrinhoEntityById(mockCarrinhoEntity.getId())).thenReturn(mockCarrinhoEntity);
		Mockito.when(repository.save(mockCarrinhoEntity)).thenReturn(mockCarrinhoEntity);
		
		service.create(inputObject.mockCarrinhoDTO());
	}
	
	@Test
	public void findAll() {
		Mockito.when(repository.findAll()).thenReturn(inputObject.mockListCarrinhoEntity());
		List<CarrinhoDTO> findAll = service.findAll();
		
		assertEquals(11, findAll.size());
	}
	
	@Test
	public void findById() {
		CarrinhoEntity mockCarrinhoEntity = inputObject.mockCarrinhoEntity();
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(mockCarrinhoEntity));
		Mockito.when(repository.save(mockCarrinhoEntity)).thenReturn(mockCarrinhoEntity);
		CarrinhoDTO find = service.findById(1L);
		
		assertEquals(0L, find.getId());
	}
}
