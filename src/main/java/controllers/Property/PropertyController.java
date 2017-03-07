package controllers.Property;

import controllers.AbstractController;
import domain.*;
import forms.EditAuditForm;
import forms.EditPropertyForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import security.LoginService;
import services.ActorService;
import services.AuditService;
import services.PropertyService;
import services.RequestService;

import java.util.Collection;

@Controller
@RequestMapping("/property")
public class PropertyController extends AbstractController {

    @Autowired
    PropertyService propertyService;
    @Autowired
    AuditService auditService;

    @Autowired
    ActorService actorService;

    @Autowired
    RequestService requestService;

    @RequestMapping("/list")
    public ModelAndView listProperties() {
        ModelAndView result = new ModelAndView("property/list");
        Collection<Property> properties = propertyService.findAll();
        result.addObject("properties",properties);
        result.addObject("requestURI","property/list.do");

        return result;
    }

    @RequestMapping("/{property}")
    public ModelAndView index(@PathVariable Property property) {
        ModelAndView result = new ModelAndView("property/index");
        result.addObject("property",property);
        if (LoginService.isAuthorized()) {
            result.addObject("user", actorService.findActorByPrincipal());
        }
        result.addObject("request",new Request());
        result.addObject("requestURI","property/index.do");

        return result;
    }
    @RequestMapping("/{property}/audit")
    public ModelAndView audit(@PathVariable Property property) {
        ModelAndView result = new ModelAndView("property/audit");
        EditAuditForm form=new EditAuditForm();
        form.setAudit(new Audit());
        form.setPropertyId(property.getId());
        
        result.addObject("form",form);

        return result;
    }
    
    @RequestMapping(value = "/{property}/audit",method = RequestMethod.POST)
    public ModelAndView createPost(@ModelAttribute("form") EditAuditForm form,
                                 BindingResult bindingResult){
    	Integer id=form.getPropertyId();
        Audit audit = auditService.reconstruct(form.getAudit(),bindingResult);
        ModelAndView result;
        if (bindingResult.hasErrors()){
            result= new ModelAndView("property/audit");
            result.addObject("form",form);
            return result;
        }else{
            try{
                Property p=propertyService.findOne(id);
                Audit a=auditService.save(audit);
                p.getAudits().add(a);
                Property p2=propertyService.save(p);
                a.setProperty(p2);
                auditService.save(a);
                return new ModelAndView("redirect:/property/audits/"+id+".do");
            }catch (Throwable oops){
            	 result= new ModelAndView("property/audit");
                 result.addObject("form",form);
                 return result;
            }
        }
    }
    
    
    
    
    @RequestMapping("/audits/{property}")
    public ModelAndView audits(@PathVariable Property property) {
        ModelAndView result = new ModelAndView("property/audits");
        
        result.addObject("audits",property.getAudits());
        result.addObject("requestURI","property/audits/"+property.getId()+".do");

        return result;
    }

    @RequestMapping(value = "/{property}",method = RequestMethod.POST)
    public ModelAndView index(@PathVariable Property property, @ModelAttribute("request") Request request,
                              BindingResult bindingResult) {
        checkIsTenant();
        request = requestService.reconstruct(request,bindingResult,property);
        try{
            requestService.save(request);
        }catch (Throwable throwable){

        }

        return new ModelAndView("redirect:/property/"+property.getId()+".do");
    }

    private void checkIsTenant() {
        Actor actor = actorService.findActorByPrincipal();
        Assert.isTrue(actor instanceof Tenant);
    }


}
