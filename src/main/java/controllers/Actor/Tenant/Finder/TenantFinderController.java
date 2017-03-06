package controllers.Actor.Tenant.Finder;

import controllers.AbstractController;
import domain.Finder;
import domain.Property;
import domain.Tenant;
import forms.EditPropertyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.FinderService;

import java.util.Collection;

@Controller
@RequestMapping("/actor/tenant/finder")
public class TenantFinderController extends AbstractController {

    @Autowired
    ActorService actorService;

    @Autowired
    FinderService finderService;

    @RequestMapping(value = "/configure")
    public ModelAndView editPost(){
        ModelAndView result = new ModelAndView("actor/tenant/finder");
        Tenant tenant = (Tenant) actorService.findActorByPrincipal();
        Finder finder = (tenant.getFinder()!=null) ? tenant.getFinder() : new Finder();
        result.addObject("finder",finder);

        return result;
    }

    @RequestMapping(value = "/configure",method = RequestMethod.POST)
    public ModelAndView configure(@ModelAttribute("finder") Finder finder,
                                 BindingResult bindingResult){
        ModelAndView result;
        Assert.notNull(finder);
        finder = finderService.reconstruct(finder,bindingResult);
            try{
                finderService.save(finder);
            }catch (Throwable oops){
                System.out.println(oops.getLocalizedMessage());
            }
        return new ModelAndView("redirect:/actor/tenant/finder/configure.do");
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(){
        ModelAndView result;
        Tenant tenant = (Tenant) actorService.findActorByPrincipal();
        Collection<Property> properties = finderService.executeFinder(tenant.getFinder());
        result =  new ModelAndView("actor/tenant/search");
        result.addObject("properties",properties);

        return result;
    }

}
