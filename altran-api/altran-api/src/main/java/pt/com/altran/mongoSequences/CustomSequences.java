package pt.com.altran.mongoSequences;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "tb_sequences")
@Data
public class CustomSequences {
	 @Id
	 private String id;
	 private int seq;
}
