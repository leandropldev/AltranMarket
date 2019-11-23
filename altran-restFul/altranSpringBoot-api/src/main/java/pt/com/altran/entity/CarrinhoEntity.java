package pt.com.altran.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("tb_carrinho")
public class CarrinhoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private UsuarioEntiy usuario;
	private List<ItemEntity> itens = new ArrayList<>();
}
