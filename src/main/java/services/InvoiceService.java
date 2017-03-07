package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InvoiceRepository;
import domain.Invoice;

@Service
@Transactional
public class InvoiceService {

	// Managed repositories
	@Autowired
	private InvoiceRepository invoiceRepository;

	// Constructor
	public InvoiceService() {
		super();
	}

	// CRUD Methods

	public Collection<Invoice> findAll() {
		Collection<Invoice> result;
		result = invoiceRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Invoice findOne(int invoiceId) {
		Assert.isTrue(invoiceId != 0);
		Invoice result;
		result = invoiceRepository.findOne(invoiceId);
		Assert.notNull(result);
		return result;

	}

	public void save(Invoice invoice) {
		Assert.notNull(invoice);
		invoiceRepository.save(invoice);
	}

	public void delete(Invoice invoice) {
		Assert.notNull(invoice);
		Assert.isTrue(invoice.getId() != 0);
		Assert.isTrue(invoiceRepository.exists(invoice.getId()));
		invoiceRepository.delete(invoice);
	}

	public Double totalSumOfMoney(){
		return invoiceRepository.totalSumOfMoney();
	}
}
