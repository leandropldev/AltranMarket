package pt.com.altran.controller;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pt.com.altran.AltranSpringBootApiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AltranSpringBootApiApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {
   
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
	
	protected <T> T mapFromJson(String json, Class<T> clazz)
		throws JsonParseException, JsonMappingException, IOException {
      
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
   }
}