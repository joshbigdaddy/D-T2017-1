package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Attribute;
import domain.Audit;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute,Integer>{
	/**
	 * A listing in which the attributes are sorted in descending order regarding the
number of times they have been used to describe a property ESTA FALTA
	 */
}