package pt.com.altran.entity;

import java.io.Serializable;

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
@Document("tb_item_carrinho")
public class ItemCarrinhoEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;
	private double valor_unitario;
	private int quantidade;
	private double valor_somado;
}
