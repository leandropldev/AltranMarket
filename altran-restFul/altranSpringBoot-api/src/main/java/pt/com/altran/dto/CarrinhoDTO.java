package pt.com.altran.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.com.altran.entity.ItemEntity;
import pt.com.altran.entity.UsuarioEntiy;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.Generated
public class CarrinhoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private UsuarioEntiy usuario;
	@Builder.Default
	private List<ItemEntity> itens = new ArrayList<>();
}
