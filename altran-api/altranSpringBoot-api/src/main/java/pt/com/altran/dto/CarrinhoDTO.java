package pt.com.altran.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.Generated
public class CarrinhoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private UsuarioDTO usuario;
	@Builder.Default
	private List<ItemDTO> itens = new ArrayList<>();
}
