package pt.com.altran.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.com.altran.entity.UsuarioEntiy;

public interface UsuarioRepository extends MongoRepository<UsuarioEntiy, Long> {
	
	UsuarioEntiy findUsuarioEntiyById(long id);
	Optional<UsuarioEntiy> findUsuarioEntiyByNomeOrEmail(String nome, String email);
}
