package pt.com.altran.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;
	private String email;
}
