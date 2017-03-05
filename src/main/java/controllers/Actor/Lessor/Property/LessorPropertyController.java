package controllers.Actor.Lessor.Property;

import controllers.AbstractController;
import domain.Attribute;
import domain.AttributeValue;
import domain.Lessor;
import domain.Property;
import forms.EditPropertyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.AttributeService;
import services.PropertyService;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/actor/lessor/property")
public class LessorPropertyController extends AbstractController {


    @Autowired
    ActorService actorService;

    @Autowired
    PropertyService propertyService;

    @Autowired
    AttributeService attributeService;

    @RequestMapping("/list")
    public ModelAndView listProperties() {
        ModelAndView result = new ModelAndView("actor/lessor/property/list");
        Lessor lessor = (Lessor) actorService.findActorByPrincipal();
        Collection<Property> properties = lessor.getProperties();
        result.addObject("properties",properties);
        result.addObject("requestURI","actor/lessor/property/list.do");

        return result;
    }

    @RequestMapping(value = "/delete/{property}")
    public ModelAndView deleteRecipe(@PathVariable Property property) {
        System.out.println(property);
        Assert.notNull(property);
        try {
            propertyService.delete(property);
        } catch (Throwable throwable) {
            System.out.print(throwable.getLocalizedMessage());
        }
        return new ModelAndView("redirect:/actor/lessor/property/list.do");
    }


    @RequestMapping("/new")
    public ModelAndView newGet() {
        System.out.print("asdasa");
        return createNewView(new Property());
    }

    @RequestMapping("/edit/{property}")
    public ModelAndView editGet(@PathVariable Property property) {
        return createEditView(property);
    }

    private ModelAndView createEditView(Property property){
        Assert.notNull(property);
        Assert.isTrue(property.getLessor().getId() == actorService.findActorByPrincipal().getId());

        ModelAndView result = new ModelAndView("actor/lessor/property/edit");
        EditPropertyForm editPropertyForm = new EditPropertyForm();
        editPropertyForm.setProperty(property);
        List<AttributeValue> attributeValueList = new AutoPopulatingList<AttributeValue>(AttributeValue.class);
        attributeValueList.addAll(property.getAttributeValues());
        editPropertyForm.setAttributesValue(attributeValueList);
        result.addObject("form",editPropertyForm);

        return result;
    }

    @RequestMapping(value = "/edit/{property2}",method = RequestMethod.POST)
    public ModelAndView editPost(@PathVariable Property property2, @ModelAttribute("form") EditPropertyForm editPropertyForm,
                                 BindingResult bindingResult){
        Assert.notNull(property2);
        ModelAndView result = new ModelAndView("redirect:/actor/lessor/property/list");
        Assert.isTrue(property2.getLessor().getId() == actorService.findActorByPrincipal().getId());
        Property property = propertyService.reconstruct(editPropertyForm.getProperty(),editPropertyForm.getAttributesValue(),bindingResult,true);

        if (bindingResult.hasErrors()){
            return createEditView(property);
        }else{
            try{
                propertyService.save(property);
                return createEditView(property);
            }catch (Throwable oops){
                System.out.println(oops.getLocalizedMessage());
                return createEditView(property2);
            }
        }
    }


    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public ModelAndView createPost(@ModelAttribute("form") EditPropertyForm editPropertyForm,
                                 BindingResult bindingResult){
        ModelAndView result = new ModelAndView("redirect:/actor/lessor/property/list");
        Property property = propertyService.reconstruct(editPropertyForm.getProperty(),editPropertyForm.getAttributesValue(),bindingResult,false);

        if (bindingResult.hasErrors()){
            return createNewView(property);
        }else{
            try{
                propertyService.save(property);
                return createNewView(property);
            }catch (Throwable oops){
                return createNewView(property);
            }
        }
    }

    private ModelAndView createNewView(Property property) {
        Assert.notNull(property);

        ModelAndView result = new ModelAndView("actor/lessor/property/edit");
        EditPropertyForm editPropertyForm = new EditPropertyForm();
        editPropertyForm.setProperty(property);

        List<AttributeValue> attributeValueList = new AutoPopulatingList<AttributeValue>(AttributeValue.class);
        for(Attribute e: attributeService.findAll()){
            AttributeValue p = new AttributeValue();
            p.setAttribute(e);
            attributeValueList.add(p);
        }
        editPropertyForm.setAttributesValue(attributeValueList);
        result.addObject("form",editPropertyForm);

        return result;
    }
}
