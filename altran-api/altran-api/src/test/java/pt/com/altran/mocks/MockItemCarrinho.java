package pt.com.altran.mocks;

import java.util.ArrayList;
import java.util.List;

import pt.com.altran.dto.ItemCarrinhoDTO;
import pt.com.altran.entity.ItemCarrinhoEntity;

public class MockItemCarrinho {

	public ItemCarrinhoDTO mockItemDTO() {
		return mockCarrinhoDTO(0);
	}
	
	public ItemCarrinhoEntity mockItemEntity() {
		return mockItemCarrinhoEntity(0);
	}
	
	public List<ItemCarrinhoDTO> mockListItemCarrinhoDTO(){
		List<ItemCarrinhoDTO> list = new ArrayList<>();
		for (int i = 0; i <= 10 ; i++) {
			list.add(mockCarrinhoDTO(i));
		}
		return list;
	}
	
	public List<ItemCarrinhoEntity> mockListItemCarrinhoEntity(){
		List<ItemCarrinhoEntity> list = new ArrayList<>();
		for (int i = 0; i <= 10 ; i++) {
			list.add(mockItemCarrinhoEntity(i));
		}
		return list;
	}
	
	private ItemCarrinhoEntity mockItemCarrinhoEntity(Integer number) {
		return ItemCarrinhoEntity.builder()
				.id(number.longValue())
				.nome("Mock Nome " + number)
				.valor_unitario(number)
				.quantidade(2)
				.valor_somado(number + number)
				.build();
	}
	
	private ItemCarrinhoDTO mockCarrinhoDTO (Integer number) {
		
		return ItemCarrinhoDTO.builder()
				.id(number.longValue())
				.nome("Mock Nome " + number)
				.valor_unitario(number)
				.quantidade(2)
				.valor_somado(number + number)
				.build();
	}
}
