package pt.com.altran.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
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

import pt.com.altran.mocks.MockItem;
import pt.com.altran.service.ItensService;


public class ItensControllerTest extends AbstractTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private ItensController controller;

	@Mock
	private ItensService service;
	
	private MockItem inputObject;
	
	@Before
	public void setUp() {
		inputObject = new MockItem();
	    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void create() throws Exception {
		String inputJson = super.mapToJson(inputObject.mockItemDTO());
		
		Mockito.when(service.create(inputObject.mockItemDTO())).thenReturn(inputObject.mockItemDTO());
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/itens")
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(201, status);
	}
	
	@Test
	public void findAll() throws Exception {
		Mockito.when(service.findAll()).thenReturn(inputObject.mockListItemDTO());
		
		this.mockMvc.perform(get("/itens"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void findById() throws Exception {
		Mockito.when(service.findById(1L)).thenReturn(inputObject.mockItemDTO());
		
		this.mockMvc.perform(get("/itens/1"))
        	.andExpect(status().isOk());
	}
	
	@Test
	public void update() throws Exception {
		String inputJson = super.mapToJson(inputObject.mockItemDTO());
		Mockito.when(service.update(inputObject.mockItemDTO())).thenReturn(inputObject.mockItemDTO());
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put("/itens")
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	}
	
	@Test
	public void delete() throws Exception {
		doNothing().when(service).delete(1L);
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete("/itens/1"))
		         .andReturn();
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	}
}
