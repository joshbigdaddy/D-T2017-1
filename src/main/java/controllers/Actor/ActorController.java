package controllers.Actor;

import controllers.AbstractController;
import domain.Actor;
import domain.Lessor;
import domain.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;

import javax.validation.Valid;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

    @Autowired
    ActorService actorService;

    @RequestMapping("/register/tenant")
    public ModelAndView registerTenant() {
        return register(new Tenant(),"tenant");
    }

    @RequestMapping("/register/lessor")
    public ModelAndView registerLessor() {
        return register(new Lessor(),"lessor");
    }

    private ModelAndView register(Actor actor,String uri){
        ModelAndView result;
        result = new ModelAndView("actor/register");
        result.addObject("actor",actor);
        result.addObject("uri","actor/register/"+uri+".do");

        return  result;
    }

    @RequestMapping(value = "/register/lessor", method = RequestMethod.POST)
    public ModelAndView registerLessorPost(
            @ModelAttribute("actor") Lessor actor, BindingResult binding
    ) {

        return registerPost(actor,binding,"lessor");
    }

    @RequestMapping(value = "/register/tenant", method = RequestMethod.POST)
    public ModelAndView registerTenantPost(
            @ModelAttribute("actor") Tenant actor, BindingResult binding
    ) {

        return registerPost(actor,binding,"tenant");
    }

    private ModelAndView registerPost(
           Actor actor, BindingResult binding,String uri
    ) {
        ModelAndView result;

        if (binding.hasErrors()) {
            result = register(actor,"actor/register/"+uri+".do");
            for(ObjectError e: binding.getAllErrors()){
                System.out.println(e.getDefaultMessage()+e.getCode()+e.getObjectName());
            }
            System.out.println(binding.toString());
        } else {
            try {
                Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
                actor.getUserAccount().setPassword(md5PasswordEncoder
                        .encodePassword(actor.getUserAccount().getPassword(), null));
                actorService.register(actor,uri.toUpperCase());
                result = new ModelAndView("redirect:../");
            } catch (Throwable oops) {
                System.out.print(oops.getLocalizedMessage());
                result = register(actor,"actor/register/lessor.do");
            }

        }

        return result;
    }
}
