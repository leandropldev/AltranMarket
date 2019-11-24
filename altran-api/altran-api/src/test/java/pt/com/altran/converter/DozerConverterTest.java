package pt.com.altran.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.com.altran.dto.UsuarioDTO;
import pt.com.altran.entity.UsuarioEntiy;
import pt.com.altran.mocks.MockUsuario;

public class DozerConverterTest {
	
	private MockUsuario inputObject;
	
	@Before
	public void init() {
		inputObject = new MockUsuario();
	}

	@Test
	public void parteEntitToVOTest() {
		UsuarioDTO output = DozerConverter.parseObject(inputObject.mockUsuarioEntiy(), UsuarioDTO.class);
		Assert.assertEquals(0L, output.getId());
		Assert.assertEquals("Mock Nome 0", output.getNome());
	}
	
	@Test
	public void parseEntityListToVOTest() {
		List<UsuarioDTO> outputList = DozerConverter.parseListObjects(inputObject.mockListUsuarioEntity(), UsuarioDTO.class);
		
		UsuarioDTO outputList0 = outputList.get(0);
		Assert.assertEquals(0L, outputList0.getId());
		Assert.assertEquals("Mock Nome 0", outputList0.getNome());

		UsuarioDTO outputList5 = outputList.get(5);
		Assert.assertEquals(5L, outputList5.getId());
		Assert.assertEquals("Mock Nome 5", outputList5.getNome());

		UsuarioDTO outputList10 = outputList.get(10);
		Assert.assertEquals(10L, outputList10.getId());
		Assert.assertEquals("Mock Nome 10", outputList10.getNome());
	}
	
	@Test
	public void parseVOtoEntityTest() {
		UsuarioEntiy output = DozerConverter.parseObject(inputObject.mockUsuarioDTO(), UsuarioEntiy.class);
		Assert.assertEquals(0L, output.getId());
		Assert.assertEquals("Mock Nome 0", output.getNome());
	}
	
	@Test
	public void parseVOListToEntityListTest() {
		List<UsuarioEntiy> outputList = DozerConverter.parseListObjects(inputObject.mockListUsuarioDTO(), UsuarioEntiy.class);
		
		UsuarioEntiy outputList0 = outputList.get(0);
		Assert.assertEquals(0L, outputList0.getId());
		Assert.assertEquals("Mock Nome 0", outputList0.getNome());

		UsuarioEntiy outputList5 = outputList.get(5);
		Assert.assertEquals(5L, outputList5.getId());
		Assert.assertEquals("Mock Nome 5", outputList5.getNome());

		UsuarioEntiy outputList10 = outputList.get(10);
		Assert.assertEquals(10L, outputList10.getId());
		Assert.assertEquals("Mock Nome 10", outputList10.getNome());
	}
}
