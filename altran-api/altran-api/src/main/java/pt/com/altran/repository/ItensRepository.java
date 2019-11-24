package pt.com.altran.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pt.com.altran.entity.ItemEntity;

@Repository
public interface ItensRepository extends MongoRepository<ItemEntity, Long>{

//	@Query(value = "{'employees.name': ?0}", fields = "{'employees' : 0}")
//  Department findDepartmentByEmployeeName(String empName);
	
	ItemEntity findItemEntityById(Long id);
	ItemEntity findItemEntityByNome(String nome);
}
