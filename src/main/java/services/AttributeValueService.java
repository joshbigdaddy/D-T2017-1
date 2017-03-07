package services;

import domain.AttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import repositories.AttributeValueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
@Transactional
public class AttributeValueService {

    @Autowired
    private AttributeValueRepository attributeValueRepository;


    public Collection<AttributeValue> findAll(){
        Collection<AttributeValue> attributeValues = attributeValueRepository.findAll();
        Assert.notNull(attributeValues);


        return  attributeValues;
    }

    public AttributeValue findOne(Integer id){
        AttributeValue attributeValue = attributeValueRepository.findOne(id);

        Assert.notNull(attributeValue);


        return  attributeValue;
    }

    public AttributeValue save(AttributeValue attributeValue){
        Assert.notNull(attributeValue);

        return attributeValueRepository.save(attributeValue);
    }

    public void delete(AttributeValue attributeValue){
        Assert.notNull(attributeValue);

        attributeValueRepository.delete(attributeValue);

    }
}