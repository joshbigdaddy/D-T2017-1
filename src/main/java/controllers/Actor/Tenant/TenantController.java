package controllers.Actor.Tenant;

import controllers.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.TenantService;

@Controller
@RequestMapping("/actor/tenant")
public class TenantController extends AbstractController{


    @Autowired
    TenantService tenantService;

    @RequestMapping("/request/list")
    public ModelAndView requestList(){
        ModelAndView result = new ModelAndView("actor/tenant/request");
        result.addObject("requests", tenantService.findTenantByPrincipal().getRequests());

        return result;
    }
}
