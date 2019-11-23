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

import pt.com.altran.mocks.MockUsuario;
import pt.com.altran.service.UsuarioService;


public class UsuariosControllerTest extends AbstractTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private UsuariosController usuariosController;

	@Mock
	private UsuarioService service;
	
	private MockUsuario inputObject;
	
	@Before
	public void setUp() {
		inputObject = new MockUsuario();
	    this.mockMvc = MockMvcBuilders.standaloneSetup(usuariosController).build();
	}
	
	@Test
	public void create() throws Exception {
		String inputJson = super.mapToJson(inputObject.mockUsuarioDTO());
		
		Mockito.when(service.create(inputObject.mockUsuarioDTO())).thenReturn(inputObject.mockUsuarioDTO());
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(201, status);
	}
	
	@Test
	public void findAll() throws Exception {
		Mockito.when(service.findAll()).thenReturn(inputObject.mockListUsuarioDTO());
		
		this.mockMvc.perform(get("/usuarios"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void findById() throws Exception {
		Mockito.when(service.findById(1L)).thenReturn(inputObject.mockUsuarioDTO());
		
		this.mockMvc.perform(get("/usuarios/1"))
        	.andExpect(status().isOk());
	}
	
	@Test
	public void update() throws Exception {
		String inputJson = super.mapToJson(inputObject.mockUsuarioDTO());
		Mockito.when(service.update(inputObject.mockUsuarioDTO())).thenReturn(inputObject.mockUsuarioDTO());
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put("/usuarios")
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	}
	
	@Test
	public void delete() throws Exception {
		doNothing().when(service).delete(1L);
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/1"))
		         .andReturn();
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	}
}
