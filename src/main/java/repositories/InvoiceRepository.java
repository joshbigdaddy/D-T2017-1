package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;
import domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer>{
	
	@Query("Select sum(i.value) from Invoice i")
	Double totalSumOfMoney();
	
}