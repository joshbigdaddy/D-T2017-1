package controllers.Property;

import controllers.AbstractController;

import domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.PropertyService;

import java.util.Collection;

@Controller
@RequestMapping("/property")
public class PropertyController extends AbstractController {

    @Autowired
    PropertyService propertyService;

    @RequestMapping("/list")
    public ModelAndView listProperties() {
        ModelAndView result = new ModelAndView("property/list");
        Collection<Property> properties = propertyService.findAll();
        result.addObject("properties",properties);
        result.addObject("requestURI","property/list.do");

        return result;
    }


}
