package pt.com.altran.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.Generated
@Document("tb_carrinho")
public class CarrinhoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private UsuarioEntiy usuario;
	@Builder.Default
	private List<ItemCarrinhoEntity> itens = new ArrayList<>();
	private double valor_total;
}
