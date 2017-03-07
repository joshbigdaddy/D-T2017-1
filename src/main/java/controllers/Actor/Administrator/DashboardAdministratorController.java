import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AttributeService;
import services.FinderService;
import services.InvoiceService;
import services.LessorService;
import services.PropertyService;
import services.RequestService;
import services.TenantService;

import controllers.AbstractController;
import domain.Attribute;
import domain.Lessor;
import domain.Tenant;

@Controller
@RequestMapping("/administrator/dashboard")
public class DashboardAdministratorController extends AbstractController{
	
	//SERVICES ------------------------------------------

	
	@Autowired
	private ActorService actorService;
	@Autowired
	private AttributeService attributeService;
	@Autowired
	private FinderService finderService;
	@Autowired
	private LessorService lessorService;
	@Autowired
	private TenantService tenantService;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private RequestService requestService;
	@Autowired
	private InvoiceService invoiceService;
	//Constructor
	
	public DashboardAdministratorController() {
		super();
	}
	
	//Methods
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView displayDashboard(){
		ModelAndView result;
		
				Double	avgAcceptedRequestLessor= ;
				Double avgDeniedRequestLessor= ;
				Double avgDeniedRequestTenant= ;
				Double avgAcceptedRequestTenant= ;
				Collection<Lessor> lessorApprovedMoreRequest=lessorService.maxRequestsApprovedLessor();
				Collection<Lessor> lessorDeniedMoreRequest=lessorService.maxRequestsDeniedLessor();
				Collection<Lessor> lessorPendingMoreRequest= lessorService.maxRequestsPendingLessor();
				Collection<Tenant> tenantPendingMoreRequest=tenantService.maxRequestsPendingTenant();
				Collection<Tenant> tenantApprovedMoreRequest= tenantService.maxRequestsApprovedTenant();
				Collection<Tenant> tenantDeniedMoreRequest=tenantService.maxRequestsDeniedTenant();
				lessorRatioMaxVsMin=; 
				tenantRatioMaxVsMin=;
				minResultsPerFinder= ;
				maxResultsPerFinder= ;
				avgResultsPerFinder=;
				Double minAuditPerProperty=propertyService.minAuditsPerProperty();
				Double maxAuditPerProperty= propertyService.maxAuditsPerProperty();
				Double avgAuditPerProperty= propertyService.avgAuditsPerProperty();;
				Collection<Attribute> attributeDescribePropertyDesc= attributeService.getAllAttributesByNumberOfTimesInProperty();
				Integer minSocialIdentityPerActor=actorService.minSocialIdentitiesPerActor();
				Integer maxSocialIdentityPerActor=actorService.maxSocialIdentitiesPerActor();
				Double avgSocialIdentityPerActor=actorService.avgSocialIdentitiesPerActor();
				Integer maxInvoicePerTenant= tenantService.maxInvoicesPerTenant();
				Integer minInvoicePerTenant= tenantService.minInvoicesPerTenant();
				Double avgInvoicePerTenant= tenantService.avgInvoicesPerTenant();
				Double invoicesAmount= invoiceService.totalSumOfMoney();
				Double avgRequestPerPropertyWithAudits=propertyService.avgRequestsPerPropertyWithAudits();
				Double avgRequestPerPropertyWithoutAudits=propertyService.avgRequestsPerPropertyWithoutAudits();

				
				
				
		
		result= createDashboardModelAndView(avgAcceptedRequestLessor,avgDeniedRequestLessor,avgDeniedRequestTenant
				,avgAcceptedRequestTenant,lessorApprovedMoreRequest,lessorDeniedMoreRequest,lessorPendingMoreRequest
				,tenantPendingMoreRequest,tenantApprovedMoreRequest,tenantDeniedMoreRequest,lessorRatioMaxVsMin
				,tenantRatioMaxVsMin,minResultsPerFinder,maxResultsPerFinder,avgResultsPerFinder,minAuditPerProperty
				,maxAuditPerProperty,avgAuditPerProperty,attributeDescribePropertyDesc,minSocialIdentityPerActor
				,maxSocialIdentityPerActor,avgSocialIdentityPerActor,maxInvoicePerTenant,minInvoicePerTenant
				,avgInvoicePerTenant,invoicesAmount,avgRequestPerPropertyWithAudits,avgRequestPerPropertyWithoutAudits);
		return result;
	}

	protected ModelAndView createDashboardModelAndView(){
		ModelAndView result;
		
		result = new ModelAndView("administrator/dashboard");
		
		
		result.addObject("usersAvgLikes",usersAvgLikes);
		result.addObject("usersAvgDislikes",usersAvgDislikes);

		return result;
	}
}