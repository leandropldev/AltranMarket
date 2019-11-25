package pt.com.altran.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.Generated
public class ItemCarrinhoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;
	private double valor_unitario;
	private int quantidade;
	private double valor_somado;
}
