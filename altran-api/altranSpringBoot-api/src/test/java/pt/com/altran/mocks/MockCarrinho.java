package pt.com.altran.mocks;

import java.util.ArrayList;
import java.util.List;

import pt.com.altran.dto.CarrinhoDTO;
import pt.com.altran.entity.CarrinhoEntity;

public class MockCarrinho {
	
	MockItem item = new MockItem();
	MockUsuario usuario = new MockUsuario();
	
	public CarrinhoDTO mockCarrinhoDTO() {
		return mockCarrinhoDTO(0);
	}
	
	public CarrinhoEntity mockCarrinhoEntity() {
		return mockCarrinhoEntity(0);
	}
	
	public List<CarrinhoDTO> mockListCarrinhoDTO(){
		List<CarrinhoDTO> list = new ArrayList<>();
		for (int i = 0; i <= 10 ; i++) {
			list.add(mockCarrinhoDTO(i));
		}
		return list;
	}
	
	public List<CarrinhoEntity> mockListCarrinhoEntity(){
		List<CarrinhoEntity> list = new ArrayList<>();
		for (int i = 0; i <= 10 ; i++) {
			list.add(mockCarrinhoEntity(i));
		}
		return list;
	}
	
	private CarrinhoEntity mockCarrinhoEntity(Integer number) {
		return CarrinhoEntity.builder()
				.id(number.longValue())
				.usuario(usuario.mockUsuarioEntiy())
				.itens(item.mockListItemEntity())
				.build();
	}
	
	private CarrinhoDTO mockCarrinhoDTO (Integer number) {
		return CarrinhoDTO.builder()
				.id(number.longValue())
				.usuario(usuario.mockUsuarioDTO())
				.itens(item.mockListItemDTO())
				.build();
	}
}
