package controllers.Actor;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SocialIdentityService;
import domain.SocialIdentity;
import domain.SocialUser;

@Controller
@RequestMapping("/actor/socialidentities")
public class SocialIdentitiesController {

	@Autowired
	private ActorService actorService;

	@Autowired
	private SocialIdentityService socialIdentityService;

	@RequestMapping("/list")
	public ModelAndView listSocialIdentities() {

		SocialUser user;
		user = (SocialUser) actorService.findActorByPrincipal();
		ModelAndView result = new ModelAndView("actor/socialidentities/list");
		result.addObject("socialidentities", user.getSocialIdentities());
		result.addObject("requestURI", "actor/socialidentities/list.do");

		return result;

	}

    @RequestMapping("/new")
    public ModelAndView newSocialIdentities() {

        return createEditModelAndView(new SocialIdentity());
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, params = "save")
    public ModelAndView newSocialIdentitiesPost(@ModelAttribute("socialIdentity") SocialIdentity socialIdentity, BindingResult binding) {
        return editOrNewSI(socialIdentity,binding);

    }

	@RequestMapping("/edit")
	public ModelAndView editSocialIdentities(@RequestParam SocialIdentity id) {

		ModelAndView result = createEditModelAndView(id);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView editSocialIdentities(@RequestParam SocialIdentity id,
                                             @ModelAttribute("socialIdentity") SocialIdentity socialIdentity, BindingResult binding) {
        Assert.isTrue(id.getId() == socialIdentity.getId());
        Assert.isTrue(actorService.findActorByPrincipal().getSocialIdentities().contains(id));
        return editOrNewSI(socialIdentity,binding);

	}

	private ModelAndView editOrNewSI(SocialIdentity socialIdentity,BindingResult binding){
        ModelAndView result;

        if (binding.hasErrors()) {
            result = createEditModelAndView(socialIdentity);
        } else {
            try {
                socialIdentity.setActor(actorService.findActorByPrincipal());
                socialIdentityService.save(socialIdentity);
                result = new ModelAndView(
                        "redirect:/actor/socialidentities/list.do");
            } catch (Throwable oops) {
                result = createEditModelAndView(socialIdentity, "wrong");
            }

        }

        return result;
    }
	@RequestMapping(value = "/delete")
	public ModelAndView deleteCategory(@RequestParam SocialIdentity id) {
		ModelAndView result;
		SocialIdentity socialIdentity = id;
		try {

			SocialUser user;
			user = (SocialUser) actorService.findActorByPrincipal();
			Collection<SocialIdentity> redes = new ArrayList<>(
					user.getSocialIdentities());
			redes.remove(id);
			user.setSocialIdentities(redes);
			socialIdentityService.delete(socialIdentity);
			result = new ModelAndView(
					"redirect:/actor/socialidentities/list.do");

		} catch (Throwable oops) {
			System.out.print(oops.getMessage());
			result = createListModelAndView(socialIdentity, "wrong");
		}
		return result;
	}

	private ModelAndView createEditModelAndView(SocialIdentity socialIdentity) {

		return createEditModelAndView(socialIdentity, null);

	}

	private ModelAndView createEditModelAndView(SocialIdentity socialIdentity,
			String message) {

		ModelAndView result;
		result = new ModelAndView("actor/socialidentities/edit");
		result.addObject("socialidentity", socialIdentity);
		result.addObject("message", message);
		return result;
	}

	private ModelAndView createListModelAndView(SocialIdentity socialIdentity) {

		return createListModelAndView(socialIdentity, null);

	}

	private ModelAndView createListModelAndView(SocialIdentity socialIdentity,
			String message) {

		Collection<SocialIdentity> res;
		ModelAndView result;
		result = new ModelAndView("actor/socialidentities/list");
		result.addObject("socialidentity", socialIdentity);
		result.addObject("message", message);
		return result;
	}

}
