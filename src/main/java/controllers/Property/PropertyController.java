package controllers.Property;

import controllers.AbstractController;
import domain.Actor;
import domain.Lessor;
import domain.Property;
import domain.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
