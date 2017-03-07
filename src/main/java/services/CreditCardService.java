package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CreditCardRepository;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	// Managed repositories
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private Validator validator;

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

	public CreditCard save(CreditCard creditCard) {
		Assert.notNull(creditCard);
		CreditCard c=creditCardRepository.save(creditCard);
		return c;
	}

	public void delete(CreditCard creditCard) {
		Assert.notNull(creditCard);
		Assert.isTrue(creditCard.getId() != 0);
		Assert.isTrue(creditCardRepository.exists(creditCard.getId()));
		creditCardRepository.delete(creditCard);
	}
	  public CreditCard reconstruct(CreditCard creditCard, BindingResult bindingResult) {
	        CreditCard result;

	        if (creditCard.getId()==0){
	            result = creditCard;
	        }else {
	            result = creditCardRepository.findOne(creditCard.getId());
	            result.setBrandName(creditCard.getBrandName());
	            result.setHolderName(creditCard.getHolderName());
	            result.setExpirationMonth(creditCard.getExpirationMonth());
	            result.setExpirationYear(creditCard.getExpirationYear());
	            result.setFee(creditCard.getFee());
	            result.setNumber(creditCard.getNumber());
	            result.setCvvCode(creditCard.getCvvCode());
				validator.validate(result, bindingResult); 
	        }
	        


	        return result;
	    }
}
