package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Attribute;
import domain.Audit;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute,Integer>{
	
}