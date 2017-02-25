package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Audit;
import domain.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Integer>{
	
}