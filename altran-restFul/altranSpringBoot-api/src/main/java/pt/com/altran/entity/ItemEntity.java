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
@Document("tb_itens")
public class ItemEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private long id;
	private String nome;
	private double valor;
}
