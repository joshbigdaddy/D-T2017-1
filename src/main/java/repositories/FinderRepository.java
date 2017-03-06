package repositories;

import domain.Property;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;
import domain.Finder;

import java.util.Collection;

@Repository
public interface FinderRepository extends JpaRepository<Finder,Integer> {

    @Query("select p from Property p join p.attributeValues av join av.attribute a where (a.name = 'city' and av.value=?1) and (p.rate>?2 and p.rate<?3 ) and (p.name like %?4% or p.address like %?4% or p.description like %?4%)")
    Collection<Property> executeFinder(String city, Integer minRate, Integer maxRate, String keyword);

}