package pt.com.altran.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.com.altran.entity.CarrinhoEntity;

public interface CarrinhoRepository extends MongoRepository<CarrinhoEntity, Long>{
	CarrinhoEntity findCarrinhoEntityById(Long id);
}
