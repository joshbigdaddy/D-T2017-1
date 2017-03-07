package services;

import java.util.Collection;
import java.util.Date;

import domain.Request;
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

	@Autowired
    private RequestService requestService;

	@Autowired
    private ConfigurationService configurationService;

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

	public Invoice save(Invoice invoice) {
		Assert.notNull(invoice);
		return invoiceRepository.save(invoice);
	}

	public void delete(Invoice invoice) {
		Assert.notNull(invoice);
		Assert.isTrue(invoice.getId() != 0);
		Assert.isTrue(invoiceRepository.exists(invoice.getId()));
		invoiceRepository.delete(invoice);
	}

    public Invoice generateInvoice(Request request) {
	    Assert.notNull(request);
		Invoice result = new Invoice();
		result.setAmount(requestService.getAmount(request));
		result.setCreditCardNumber(request.getTenant().getCreditCard().getNumber());
		result.setTenant(request.getTenant());
		result.setVatNumber(configurationService.getFirstConfiguration().getVat());
		result.setMoment(new Date());
		result.setRequest(request);

		return save(result);
    }

	public Double totalSumOfMoney(){
		return invoiceRepository.totalSumOfMoney();
	}
}
