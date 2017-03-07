package controllers.Actor.Tenant;

import controllers.AbstractController;
import domain.CreditCard;
import domain.Invoice;
import domain.Property;
import domain.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.InvoiceService;
import services.TenantService;

@Controller
@RequestMapping("/actor/tenant")
public class TenantController extends AbstractController{


    @Autowired
    TenantService tenantService;

    @Autowired
    InvoiceService invoiceService;

    @RequestMapping("/request/list")
    public ModelAndView requestList(){
        ModelAndView result = new ModelAndView("actor/tenant/request");
        result.addObject("requests", tenantService.findTenantByPrincipal().getRequests());

        return result;
    }


    @RequestMapping("/invoice/{request}")
    public ModelAndView invoice(@PathVariable Request request){
        Assert.notNull(request);
        Assert.isTrue(request.getTenant() == tenantService.findTenantByPrincipal());
        Assert.isTrue(request.getState() == Request.RequestType.ACCEPTED);
        ModelAndView result = new ModelAndView("actor/tenant/invoice");
        Invoice invoice = getInvoice(request);
       result.addObject("invoice",invoice);
       result.addObject("creditcard",hideCreditCard(invoice.getCreditCardNumber()));

        return result;
    }

    private String hideCreditCard(String creditCard) {
        char[] charArray = creditCard.toCharArray();
        for(int i =0;i<charArray.length;i++){
            if(i>3 && i<(charArray.length-4)){
                charArray[i] = '*';
            }
        }
        return String.valueOf(charArray);
    }

    private Invoice getInvoice(Request request) {
        Invoice invoice = request.getInvoice();
        if (invoice==null){
            invoice = invoiceService.generateInvoice(request);
        }
        return invoice;
    }
}
