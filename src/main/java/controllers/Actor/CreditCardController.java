package controllers.Actor;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import domain.Actor;
import domain.CreditCard;
import domain.Lessor;
import domain.SocialUser;
import domain.Tenant;
import forms.EditCreditCardForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.CreditCardService;
import services.LessorService;
import services.TenantService;

@Controller
@RequestMapping("/actor/creditcard")
public class CreditCardController {

    @Autowired
    ActorService actorService;
    
    @Autowired
    CreditCardService creditCardService;
    
    @Autowired
    LessorService lessorService;
    
    @Autowired
    TenantService tenantService;

    @RequestMapping("/edit")
    public ModelAndView editCreditCard(){
        ModelAndView result = new ModelAndView("actor/creditcard/edit");
        checkIsTenantOrLessor();
        SocialUser user =(SocialUser) actorService.findActorByPrincipal();
        CreditCard creditCard = (user.getCreditCard()!=null)? user.getCreditCard() : new CreditCard();
        EditCreditCardForm editCreditCardForm = new EditCreditCardForm();
        editCreditCardForm.setCreditCard(creditCard);
        result.addObject("creditcard",editCreditCardForm);

        return result;
    }

    private void checkIsTenantOrLessor() {
        Actor actor = actorService.findActorByPrincipal();
        Assert.isTrue(actor instanceof SocialUser);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ModelAndView editCreditCard(@ModelAttribute("creditcard") EditCreditCardForm editCreditCardForm,
                                 BindingResult bindingResult){
        ModelAndView result;

        result = new ModelAndView("redirect:/actor/creditcard/edit.do");
        System.out.println("hola");
        CreditCard creditCard = creditCardService.reconstruct(editCreditCardForm.getCreditCard(),bindingResult);
       if (bindingResult.hasErrors()){
           System.out.println("hola2");

       		return createEditModelAndView(editCreditCardForm,"commit.error");
        }else{
            try{
                System.out.println("hola3");

                Date ahora=new Date();
                Calendar fechac = new GregorianCalendar();
                fechac.set(creditCard.getExpirationYear(),creditCard.getExpirationMonth(),7);
                Calendar ahorac = new GregorianCalendar();
                ahorac.setTime(ahora);


                if(!ahorac.after(fechac)){
                	Lessor l=lessorService.findLessorByPrincipal();
                	Tenant t=tenantService.findTenantByPrincipal();
                	if(l!=null){
                        System.out.println("hola4");

                		CreditCard c=creditCardService.save(creditCard);
                        System.out.println("hola5");

                		l.setCreditCard(c);
                		lessorService.save(l);

                	}
                	if(t!=null){
                		CreditCard c=creditCardService.save(creditCard);
                		t.setCreditCard(c);
                		tenantService.save(t);
                	}
                	
                }
                return result;
            }catch (Throwable oops){
                //System.out.println(oops.getLocalizedMessage());
            	result=createEditModelAndView(editCreditCardForm,"commit.error");
                return result;
            }
        }
    }
    
    protected ModelAndView createEditModelAndView(EditCreditCardForm m, String message) {
		ModelAndView result;
		result = new ModelAndView("redirect:/actor/creditcard/edit.do");
		
		result.addObject("message",message);
		return result;
	}
}
