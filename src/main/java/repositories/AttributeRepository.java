package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Attribute;
import domain.Audit;

import java.util.Collection;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute,Integer>{
	/**
	 * A listing in which the attributes are sorted in descending order regarding the
number of times they have been used to describe a property ESTA FALTA
	 */
//
//	@Query("select a from Attribute a order by (select (")
//Collection<Attribute> getAllAttributesByNumberOfTimesInProperty();
}