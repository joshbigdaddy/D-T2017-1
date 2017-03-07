/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.Actor.Administrator;

import com.sun.org.apache.xpath.internal.operations.Mod;
import controllers.AbstractController;
import domain.Administrator;
import domain.Attribute;
import domain.Auditor;
import domain.Property;
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
import services.AdministratorService;
import services.AttributeService;
import services.AuditorService;

@Controller
@RequestMapping("/actor/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AttributeService attributeService;

	@Autowired
    private AdministratorService administratorService;

	@Autowired
    private AuditorService auditorService;
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/attribute/list")
	public ModelAndView action1() {
		ModelAndView result;
				
		result = new ModelAndView("actor/administrator/attribute/list");
		result.addObject("attributes",attributeService.findAll());
		result.addObject("requestURI","actor/administrator/attribute/list.do");


		return result;
	}

    @RequestMapping(value = "/attribute/new")
    public ModelAndView newAttribute(){
	    return editOrNewAttribute(new Attribute());
    }

    @RequestMapping(value = "/attribute/edit/{attribute}")
    public ModelAndView edit(@PathVariable Attribute attribute){
        return editOrNewAttribute(attribute);
    }

    private ModelAndView editOrNewAttribute(Attribute attribute){
        Assert.notNull(attribute);
        ModelAndView result = new ModelAndView("actor/administrator/attribute/edit");
        result.addObject("attribute",attribute);

        return result;
    }

    @RequestMapping(value = "/attribute/delete/{attribute}")
    public ModelAndView deleteRecipe(@PathVariable Attribute attribute) {
        Assert.notNull(attribute);
        try {
            attributeService.delete(attribute);
        } catch (Throwable throwable) {
            System.out.print(throwable.getLocalizedMessage());
        }
        return new ModelAndView("redirect:/actor/administrator/attribute/list.do");
    }

    @RequestMapping(value = "/attribute/edit/{attribute}",method = RequestMethod.POST)
    public ModelAndView editPost(@PathVariable Attribute attribute, @ModelAttribute("attribute") Attribute attribute2,
                                 BindingResult bindingResult){
        Assert.isTrue(attribute.getId() == attribute2.getId());
        return editPostView(attribute2,bindingResult);

    }

    @RequestMapping(value = "/attribute/new",method = RequestMethod.POST)
    public ModelAndView newPost(@ModelAttribute("attribute") Attribute attribute,
                                 BindingResult bindingResult){
        return editPostView(attribute,bindingResult);

    }

    private ModelAndView editPostView(Attribute attribute, BindingResult bindingResult){
        Assert.notNull(attribute);
        if (bindingResult.hasErrors()){
            return editOrNewAttribute(attribute);
        }else{
            try{
                attributeService.save(attribute);
                return new ModelAndView("redirect:list.do");
            }catch (Throwable oops){
                System.out.println(oops.getLocalizedMessage());
                return editOrNewAttribute(attribute);
            }
        }
    }

    @RequestMapping("/auditor/new")
    public ModelAndView createAuditor(){
        ModelAndView result = new ModelAndView("actor/administrator/registerAuditor");
        result.addObject("actor",new Auditor());

        return result;
    }


    @RequestMapping(value = "/auditor/new",method = RequestMethod.POST)
    public ModelAndView createAuditorPost(@ModelAttribute("actor") Auditor auditor,BindingResult binding) {
        ModelAndView result;
        if (binding.hasErrors()) {
            result = new ModelAndView("actor/administrator/registerAuditor");
            result.addObject("actor", auditor);

            return result;
        } else {
            try {
                Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
                auditor.getUserAccount().setPassword(md5PasswordEncoder
                        .encodePassword(auditor.getUserAccount().getPassword(), null));
                auditor.setAudits(null);
                auditorService.save(auditor);
                result = new ModelAndView("redirect:../../");
                return result;
            } catch (Throwable oops) {
                System.out.println(oops.getMessage());
                result = new ModelAndView("actor/administrator/registerAuditor");
                result.addObject("actor", auditor);

                return result;
            }
        }
    }

}