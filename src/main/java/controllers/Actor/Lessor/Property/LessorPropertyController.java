package controllers.Actor.Lessor.Property;

import controllers.AbstractController;
import domain.Lessor;
import domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.PropertyService;

import java.util.Collection;
@Controller
@RequestMapping("/actor/lessor/property")
public class LessorPropertyController extends AbstractController {


    @Autowired
    ActorService actorService;

    @Autowired
    PropertyService propertyService;

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

}
