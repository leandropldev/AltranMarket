package pt.com.altran.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.com.altran.entity.CarrinhoEntity;

public interface CarrinhoRepository extends MongoRepository<CarrinhoEntity, Long>{
	Optional<CarrinhoEntity> findCarrinhoEntityById(Long id);
}
