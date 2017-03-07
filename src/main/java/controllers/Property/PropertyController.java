package controllers.Property;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
import java.util.Date;

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

    @RequestMapping("/{property}/audit/delete")
    public ModelAndView deleteAudit(@PathVariable Property property) {
        ModelAndView result = new ModelAndView("redirect:../audit.do");
        Audit audit = auditService.findByAuditor(actorService.findActorByPrincipal(),property);
        if (audit==null){
            return result;
        }
        Assert.isTrue(!audit.getFinal());
        auditService.delete(audit);

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
        Audit audit = auditService.findByAuditor(actorService.findActorByPrincipal(),property);
        if (audit==null) audit = new Audit();
        form.setAudit(audit);
        form.setPropertyId(property.getId());
        result.addObject("canaudit",canAudit((Auditor) actorService.findActorByPrincipal(),property));
        result.addObject("form",form);
        result.addObject("uri","property/"+property.getId()+"/audit/");

        return result;
    }

    private Boolean canAudit(Auditor actorByPrincipal, Property property) {
        Boolean result = true;
        for(Audit a:actorByPrincipal.getAudits()){
            if (a.getProperty().getId()==property.getId() && a.getFinal()){
                result = false;
                break;
            }
        }
        return result;
    }

    @RequestMapping(value = "/{property}/audit",method = RequestMethod.POST)
    public ModelAndView createPost(@PathVariable Property property, @ModelAttribute("form") EditAuditForm form,
                                 BindingResult bindingResult){
    	Integer id=form.getPropertyId();
        Audit audit = auditService.reconstruct(form.getAudit(),bindingResult);
        ModelAndView result;
        if (!canAudit((Auditor) actorService.findActorByPrincipal(),property)) {
            return new ModelAndView("redirect:../../" + property.getId() + ".do");
        }
        if (bindingResult.hasErrors()){
            result= new ModelAndView("property/audit");
            result.addObject("form",form);
            return result;
        }else{
            try{
                audit.setProperty(propertyService.findOne(id));
                audit.setAuditor((Auditor) actorService.findActorByPrincipal());
                audit.setMoment(new Date());
                auditService.save(audit);
                return new ModelAndView("redirect:/property/audits/"+id+".do");
            }catch (Throwable oops){
            	 result= new ModelAndView("property/audit");
            	 System.out.print(oops.getMessage());
                 result.addObject("form",form);
                 return result;
            }
        }
    }
    
    
    
    
    @RequestMapping("/audits/{property}")
    public ModelAndView audits(@PathVariable Property property) {
        ModelAndView result = new ModelAndView("property/audits");
        
        result.addObject("audits",auditService.findAllFinal(property));
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
