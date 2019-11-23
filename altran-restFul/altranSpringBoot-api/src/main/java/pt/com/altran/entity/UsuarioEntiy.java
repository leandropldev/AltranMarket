package pt.com.altran.entity;

import java.io.Serializable;

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
@Document("tb_users")
public class UsuarioEntiy implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private String nome;
	private String email;
}
