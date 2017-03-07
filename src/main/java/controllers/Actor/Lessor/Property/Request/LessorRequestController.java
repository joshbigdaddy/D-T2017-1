package controllers.Actor.Lessor.Property.Request;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.*;



@Controller
@RequestMapping("/actor/lessor/property/request")
public class LessorRequestController extends AbstractController {


    @Autowired
    ActorService actorService;

    @Autowired
    PropertyService propertyService;

    @Autowired
    AttributeService attributeService;

    @Autowired
    RequestService requestService;

    @Autowired
    LessorService lessorService;

    @RequestMapping(value = "/accept/{request}")
    public ModelAndView acceptRequest(@PathVariable Request request) {
        Assert.notNull(request);
        Assert.isTrue(request.getProperty().getLessor().getId() == actorService.findActorByPrincipal().getId());
        Assert.isTrue(request.getState() == Request.RequestType.PENDING);
        Lessor actor = (Lessor) actorService.findActorByPrincipal();
        Assert.notNull(actor.getCreditCard());
        try {
            requestService.accept(request);
        } catch (Throwable throwable) {
            System.out.print(throwable.getLocalizedMessage());
        }
        return new ModelAndView("redirect:/actor/lessor/property/list.do");
    }


    @RequestMapping(value = "/reject/{request}")
    public ModelAndView rejectRequest(@PathVariable Request request) {
        Assert.notNull(request);
        Assert.isTrue(request.getProperty().getLessor().getId() == actorService.findActorByPrincipal().getId());
        Assert.isTrue(request.getState() == Request.RequestType.PENDING);
        Lessor actor = (Lessor) actorService.findActorByPrincipal();
        Assert.notNull(actor.getCreditCard());
        try {
            requestService.reject(request);
        } catch (Throwable throwable) {
            System.out.print(throwable.getLocalizedMessage());
        }
        return new ModelAndView("redirect:/actor/lessor/property/list.do");
    }
}
