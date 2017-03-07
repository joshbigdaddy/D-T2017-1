package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Attribute;

import java.util.Collection;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute,Integer>{
	/**
	 * A listing in which the attributes are sorted in descending order regarding the
number of times they have been used to describe a property 
	 */
//
	@Query("select a,(select count(at) from Property p join p.attributeValues at where at.attribute.id=a.id) as mr  from Attribute a order by mr DESC")
	Collection<Object[]> getAllAttributesByNumberOfTimesInProperty();
}