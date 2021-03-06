package controllers.Actor;

import com.sun.org.apache.xpath.internal.operations.Bool;
import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import security.LoginService;
import services.ActorService;
import services.CommentService;

import javax.validation.Valid;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

    @Autowired
    ActorService actorService;

    @Autowired
    CommentService commentService;


    @RequestMapping("/profile/{actor}")
    public ModelAndView profileUser(@PathVariable Actor actor){
        Assert.notNull(actor);
        ModelAndView result = new ModelAndView("actor/profile");
        addCommentsDataSocialUser(result,actor);
        result.addObject("actor",actor);
        result.addObject("requestURI","/actor/profile/"+actor.getId()+".do");

        return result;
    }
    @RequestMapping(value = "/profile/{actor}/comment",method = RequestMethod.POST)
    public ModelAndView postComment(@PathVariable Actor actor,@ModelAttribute("comment") Comment comment){
        Assert.notNull(actor);
        Assert.isTrue(actorsAllowComments((SocialUser) actor,(SocialUser) actorService.findActorByPrincipal()));
        commentService.newComment(comment,(SocialUser) actorService.findActorByPrincipal(),(SocialUser) actor);
        return new ModelAndView("redirect:../"+actor.getId()+".do");
        }


    private Boolean addCommentsDataSocialUser(ModelAndView result,Actor actor) {
        if (!LoginService.isAuthorized()) return false;
        Actor principal = actorService.findActorByPrincipal();
        if (!(principal instanceof SocialUser)) return false;
        if (!(actor instanceof SocialUser)) return false;
        SocialUser socialUserPrincipal = (SocialUser) principal;
        SocialUser socialUserActor = (SocialUser) actor;
        result.addObject("comments",socialUserActor.getCommentsReceived());
        Boolean hasRequestsPrincipal = actorsAllowComments(socialUserActor,socialUserPrincipal);
        if (hasRequestsPrincipal){
            result.addObject("cancomment",true);
            result.addObject("commentUri","actor/profile/"+actor.getId()+"/comment.do");
            result.addObject("comment",new Comment());
        }
        return true;
    }

    private Boolean actorsAllowComments(SocialUser socialUser1, SocialUser socialUser2){
        if (socialUser1.getId() == socialUser2.getId()) return true;
        String rolPrincipal = getRol(socialUser1);
        String rolActor = getRol(socialUser2);
        if(rolActor.equals(rolPrincipal)) return  false;
        Boolean hasRequestsPrincipal = false;
        if (rolActor.equals("Lessor")){
            hasRequestsPrincipal = checkRequestsByUsers((Tenant) socialUser1, (Lessor) socialUser2);
        }else{
            hasRequestsPrincipal = checkRequestsByUsers((Tenant) socialUser2,(Lessor) socialUser1);
        }
        return hasRequestsPrincipal;
    }

    private Boolean checkRequestsByUsers(Tenant tenant, Lessor lessor) {
        Boolean result = false;
        for(Property e:lessor.getProperties()){
            for(Request r:e.getRequests()){
                if (r.getTenant()!=null) {
                    if (r.getTenant().getId() == tenant.getId()) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return  result;
    }

    private String getRol(SocialUser socialUser){
        return (socialUser instanceof Lessor) ? "Lessor" : "Tenant";
    }

    @RequestMapping(value = "/edit")
    public ModelAndView editProfile() {
        ModelAndView result;
        Actor actor;
        result = new ModelAndView("actor/edit");
        actor = actorService.findActorByPrincipal();
        result.addObject("actor", actor);
        result.addObject("role", actor.getClass().getName());

        return result;
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView edit(
            @Valid @ModelAttribute("role") Actor actor, BindingResult binding
    ) {
        Actor principal = actorService.findActorByPrincipal();
        ModelAndView result;
        result = editProfile();

        if (binding.hasErrors()) {
            result.addObject("message", "wrong");
        } else {
            try {
                actor.setUserAccount(principal.getUserAccount());
                actorService.save(actor);
            } catch (Throwable oops) {
                result.addObject("message", "wrong");
            }

        }

        return result;
    }

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
        actor.setFinder(null);
        actor.setRequests(null);
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
                result = new ModelAndView("redirect:../../");
            } catch (Throwable oops) {
                System.out.print(oops.getLocalizedMessage());
                result = register(actor,"actor/register/"+uri+".do");
            }

        }

        return result;
    }
}
