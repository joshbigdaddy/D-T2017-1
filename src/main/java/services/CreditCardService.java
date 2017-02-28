package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	// Managed repositories
	@Autowired
	private CreditCardRepository creditCardRepository;

	// Constructor
	public CreditCardService() {
		super();
	}

	// CRUD Methods

	public Collection<CreditCard> findAll() {
		Collection<CreditCard> result;
		result = creditCardRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public CreditCard findOne(int creditCardId) {
		Assert.isTrue(creditCardId != 0);
		CreditCard result;
		result = creditCardRepository.findOne(creditCardId);
		Assert.notNull(result);
		return result;
	}

	public void save(CreditCard creditCard) {
		Assert.notNull(creditCard);
		creditCardRepository.save(creditCard);
	}

	public void delete(CreditCard creditCard) {
		Assert.notNull(creditCard);
		Assert.isTrue(creditCard.getId() != 0);
		Assert.isTrue(creditCardRepository.exists(creditCard.getId()));
		creditCardRepository.delete(creditCard);
	}
}
