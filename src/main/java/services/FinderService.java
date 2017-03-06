package services;

import java.util.Collection;

import domain.Property;
import domain.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import org.springframework.validation.BindingResult;
import repositories.FinderRepository;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	// Managed repositories
	@Autowired
	private FinderRepository finderRepository;

	@Autowired
    private ActorService actorService;

	// Constructor
	public FinderService() {
		super();
	}

	// CRUD Methods
	public Collection<Finder> findAll() {
		Collection<Finder> result;
		result = finderRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Finder findOne(int finderId) {
		Assert.isTrue(finderId != 0);
		Finder result;
		result = finderRepository.findOne(finderId);
		Assert.notNull(result);
		return result;
	}

	public void save(Finder finder) {
		Assert.notNull(finder);
		finderRepository.save(finder);
	}

	public void delete(Finder finder) {
		Assert.notNull(finder);
		Assert.isTrue(finder.getId() != 0);
		Assert.isTrue(finderRepository.exists(finder.getId()));
		finderRepository.delete(finder);
	}

    @Cacheable(value = "default")
	public Collection<Property> executeFinder(Finder finder){
        Assert.notNull(finder);
        Integer maxprice = (finder.getMaxPrice()!=null) ? finder.getMaxPrice().intValue() : 10000000;
        Integer minprice = (finder.getMinPrice()!=null) ? finder.getMinPrice().intValue() : -1;
        return finderRepository.executeFinder(finder.getCity(),
                minprice,maxprice,finder.getKeyword());
    }

    public Finder reconstruct(Finder finder, BindingResult bindingResult) {
        Tenant tenant = (Tenant) actorService.findActorByPrincipal();
        Finder result;
        if (tenant.getFinder()==null){
            result = finder;
        }else{
            result = tenant.getFinder();
            result.setCity(finder.getCity());
            result.setKeyword(finder.getKeyword());
            result.setMinPrice(finder.getMinPrice());
            result.setMaxPrice(finder.getMaxPrice());
        }
        result.setTenant(tenant);

        return result;
    }
}
