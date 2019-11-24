package pt.com.altran.mocks;

import java.util.ArrayList;
import java.util.List;

import pt.com.altran.dto.UsuarioDTO;
import pt.com.altran.entity.UsuarioEntiy;

public class MockUsuario {

	public UsuarioDTO mockUsuarioDTO() {
		return mockUsuarioDTO(0);
	}
	
	public UsuarioEntiy mockUsuarioEntiy() {
		return mockUsuarioEntity(0);
	}
	
	public List<UsuarioDTO> mockListUsuarioDTO(){
		List<UsuarioDTO> list = new ArrayList<>();
		for (int i = 0; i <= 10 ; i++) {
			list.add(mockUsuarioDTO(i));
		}
		return list;
	}
	
	public List<UsuarioEntiy> mockListUsuarioEntity(){
		List<UsuarioEntiy> list = new ArrayList<>();
		for (int i = 0; i <= 10 ; i++) {
			list.add(mockUsuarioEntity(i));
		}
		return list;
	}
	
	private UsuarioEntiy mockUsuarioEntity(Integer number) {
		return UsuarioEntiy.builder()
				.id(number.longValue())
				.nome("Mock Nome " + number)
				.email("Mock email " + number)
				.build();
	}
	
	private UsuarioDTO mockUsuarioDTO (Integer number) {
		return UsuarioDTO.builder()
				.id(number.longValue())
				.nome("Mock Nome " + number)
				.email("Mock email " + number)
				.build();
	}
}
