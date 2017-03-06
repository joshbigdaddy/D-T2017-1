package repositories;

import domain.AttributeValue;
import domain.Auditor;
import domain.Configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration,Integer>{
	
}