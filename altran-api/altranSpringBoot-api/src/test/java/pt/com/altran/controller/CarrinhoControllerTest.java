package pt.com.altran.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pt.com.altran.mocks.MockCarrinho;
import pt.com.altran.service.CarrinhoService;


public class CarrinhoControllerTest extends AbstractTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private CarrinhoController controller;

	@Mock
	private CarrinhoService service;
	
	private MockCarrinho inputObject;
	
	@Before
	public void setUp() {
		inputObject = new MockCarrinho();
	    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void create() throws Exception {
		String inputJson = super.mapToJson(inputObject.mockCarrinhoDTO());
		
		Mockito.when(service.create(inputObject.mockCarrinhoDTO())).thenReturn(inputObject.mockCarrinhoDTO());
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/carrinho")
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(201, status);
	}
	
	@Test
	public void findAll() throws Exception {
		Mockito.when(service.findAll()).thenReturn(inputObject.mockListCarrinhoDTO());
		
		this.mockMvc.perform(get("/carrinho"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void findById() throws Exception {
		Mockito.when(service.findById(1L)).thenReturn(inputObject.mockCarrinhoDTO());
		
		this.mockMvc.perform(get("/carrinho/1"))
        	.andExpect(status().isOk());
	}
}
