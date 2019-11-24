package pt.com.altran.mocks;

import java.util.ArrayList;
import java.util.List;

import pt.com.altran.dto.ItemDTO;
import pt.com.altran.entity.ItemEntity;

public class MockItem {

	public ItemDTO mockItemDTO() {
		return mockItemDTO(0);
	}
	
	public ItemEntity mockItemEntity() {
		return mockItemEntity(0);
	}
	
	public List<ItemDTO> mockListItemDTO(){
		List<ItemDTO> list = new ArrayList<>();
		for (int i = 0; i <= 10 ; i++) {
			list.add(mockItemDTO(i));
		}
		return list;
	}
	
	public List<ItemEntity> mockListItemEntity(){
		List<ItemEntity> list = new ArrayList<>();
		for (int i = 0; i <= 10 ; i++) {
			list.add(mockItemEntity(i));
		}
		return list;
	}
	
	private ItemEntity mockItemEntity(Integer number) {
		return ItemEntity.builder()
				.id(number.longValue())
				.nome("Mock Nome " + number)
				.valor(10 + number)
				.build();
	}
	
	private ItemDTO mockItemDTO (Integer number) {
		return ItemDTO.builder()
				.id(number.longValue())
				.nome("Mock Nome " + number)
				.valor(10 + number)
				.build();
	}
}
