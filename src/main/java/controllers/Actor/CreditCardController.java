package controllers.Actor;

import domain.Actor;
import domain.CreditCard;
import domain.Property;
import domain.SocialUser;
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
import security.Authority;
import services.ActorService;

@Controller
@RequestMapping("/actor/creditcard")
public class CreditCardController {

    @Autowired
    ActorService actorService;

    @RequestMapping("/edit")
    public ModelAndView editCreditCard(){
        ModelAndView result = new ModelAndView("actor/creditcard/edit");
        checkIsTenantOrLessor();
        SocialUser user = (SocialUser) actorService.findActorByPrincipal();
        CreditCard creditCard = (user.getCreditCard()!=null)? user.getCreditCard() : new CreditCard();
        result.addObject("creditcard",creditCard);

        return result;
    }

    private void checkIsTenantOrLessor() {
        Actor actor = actorService.findActorByPrincipal();
        Assert.isTrue(actor instanceof SocialUser);
    }

    @RequestMapping(value = "/edit}",method = RequestMethod.POST)
    public ModelAndView editCreditCard(@ModelAttribute("credicard") CreditCard creditCard,
                                 BindingResult bindingResult){
        ModelAndView result;
        result = new ModelAndView("redirect:/actor/credicard/edit.do");
        Assert.notNull(creditCard);
        if (bindingResult.hasErrors()){
            return result;
        }else{
            try{
                // TODO
                return result;
            }catch (Throwable oops){
                System.out.println(oops.getLocalizedMessage());
                return result;
            }
        }
    }
}
