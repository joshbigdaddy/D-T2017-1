package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Audit;
import domain.Finder;

@Repository
public interface FinderRepository extends JpaRepository<Finder,Integer>{
	
}