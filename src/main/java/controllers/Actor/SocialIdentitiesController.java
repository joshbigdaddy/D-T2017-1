package controllers.Actor;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

	@RequestMapping("/edit")
	public ModelAndView editSocialIdentities(@RequestParam int socialidentityid) {

		ModelAndView result = createEditModelAndView(socialIdentityService
				.findOne(socialidentityid));

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView editSocialIdentities(
			@Valid SocialIdentity socialIdentity, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(socialIdentity);
		} else {
			try {
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
	public ModelAndView deleteCategory(@RequestParam int socialidentityid) {
		ModelAndView result;
		SocialIdentity socialIdentity = socialIdentityService
				.findOne(socialidentityid);
		try {

			SocialUser user;
			user = (SocialUser) actorService.findActorByPrincipal();
			Collection<SocialIdentity> redes = new ArrayList<>(
					user.getSocialIdentities());
			redes.remove(socialIdentityService.findOne(socialidentityid));
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
