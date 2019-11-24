package pt.com.altran.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.com.altran.entity.UsuarioEntiy;

public interface UsuarioRepository extends MongoRepository<UsuarioEntiy, Long> {
	
	UsuarioEntiy findUsuarioEntiyById(long id);
	UsuarioEntiy findUsuarioEntiyByNomeOrEmail(String nome, String email);
}
