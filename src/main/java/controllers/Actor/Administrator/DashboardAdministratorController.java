package controllers.Actor.Administrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import domain.Actor;
import domain.Attribute;
import domain.Lessor;
import domain.Property;
import domain.Tenant;

@Controller
@RequestMapping("/actor/administrator/dashboard")
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
		
				Double	avgAcceptedRequestLessor= lessorService.avgAcceptedRequestsPerLessor();
				Double avgDeniedRequestLessor= lessorService.avgDeniedRequestsPerLessor();
				Double avgDeniedRequestTenant=tenantService.avgDeniedRequestsPerTenant();
				Double avgAcceptedRequestTenant=tenantService.avgDeniedRequestsPerTenant();
				
				Collection<Lessor> lessorApprovedMoreRequest=devuelveMaxLessor(lessorService.maxRequestsApprovedLessor());
				
				
				
				Collection<Lessor> lessorDeniedMoreRequest=devuelveMaxLessor(lessorService.maxRequestsDeniedLessor());
				Collection<Lessor> lessorPendingMoreRequest= devuelveMaxLessor(lessorService.maxRequestsPendingLessor());
				Collection<Tenant> tenantPendingMoreRequest=devuelveMaxTenant(tenantService.maxRequestsPendingTenant());
				Collection<Tenant> tenantApprovedMoreRequest= devuelveMaxTenant(tenantService.maxRequestsApprovedTenant());
				Collection<Tenant> tenantDeniedMoreRequest=devuelveMaxTenant(tenantService.maxRequestsDeniedTenant());
				Collection<Lessor> lessorRatioMax=lessorService.maxRatio(); 
				Collection<Lessor> lessorRatioMin=lessorService.maxRatio(); 
				Collection<Tenant> tenantRatioMax=tenantService.maxRatio();
				Collection<Tenant> tenantRatioMin=tenantService.minRatio(); 
				Integer minResultsPerFinder=finderService.minResultsPerFinder();
				Integer maxResultsPerFinder=finderService.maxResultsPerFinder();
				Double avgResultsPerFinder=finderService.avgResultsPerFinder();
				Double minAuditPerProperty=propertyService.minAuditsPerProperty();
				Double maxAuditPerProperty= propertyService.maxAuditsPerProperty();
				Double avgAuditPerProperty= propertyService.avgAuditsPerProperty();;
				
				Collection<Attribute> attributeDescribePropertyDesc=devuelveAttributes(attributeService.getAllAttributesByNumberOfTimesInProperty());
				
				Double minSocialIdentityPerActor=actorService.minSocialIdentitiesPerActor();
				Double maxSocialIdentityPerActor=actorService.maxSocialIdentitiesPerActor();
				
				Double avgSocialIdentityPerActor=actorService.avgSocialIdentitiesPerActor();
				Integer maxInvoicePerTenant= tenantService.maxInvoicesPerTenant();
				Integer minInvoicePerTenant= tenantService.minInvoicesPerTenant();
				Double avgInvoicePerTenant= tenantService.avgInvoicesPerTenant();
				Double invoicesAmount= invoiceService.totalSumOfMoney();
				Double avgRequestPerPropertyWithAudits=propertyService.avgRequestsPerPropertyWithAudits();
				Double avgRequestPerPropertyWithoutAudits=propertyService.avgRequestsPerPropertyWithoutAudits();

				
				
				
		
		result= createDashboardModelAndView(avgAcceptedRequestLessor,avgDeniedRequestLessor,avgDeniedRequestTenant
				,avgAcceptedRequestTenant,lessorApprovedMoreRequest,lessorDeniedMoreRequest,lessorPendingMoreRequest
				,tenantPendingMoreRequest,tenantApprovedMoreRequest,tenantDeniedMoreRequest,lessorRatioMax,lessorRatioMin
				,tenantRatioMax,tenantRatioMin,minResultsPerFinder,maxResultsPerFinder,avgResultsPerFinder,minAuditPerProperty
				,maxAuditPerProperty,avgAuditPerProperty,attributeDescribePropertyDesc,minSocialIdentityPerActor
				,maxSocialIdentityPerActor,avgSocialIdentityPerActor,maxInvoicePerTenant,minInvoicePerTenant
				,avgInvoicePerTenant,invoicesAmount,avgRequestPerPropertyWithAudits,avgRequestPerPropertyWithoutAudits);
		return result;
	}

	
	
	@RequestMapping(value = "/display/{lessor}", method = RequestMethod.GET)
	public ModelAndView display(@PathVariable Lessor lessor) {
		ModelAndView result;
		
		result = new ModelAndView("actor/administrator/dashboard/display");
		result.addObject("propertiesOrderedByAudits",propertyService.propertiesOrderByAudits(lessor.getId())) ;
		result.addObject("propertiesOrderedByDeniedRequest", devuelveProperties(propertyService.propertiesOrderByDeniedRequests(lessor.getId())));
		result.addObject("propertiesOrderedByPendingRequest", devuelveProperties(propertyService.propertiesOrderByPendingRequests(lessor.getId())));
		result.addObject("propertiesOrderedByAcceptedRequest", devuelveProperties(propertyService.propertiesOrderByAcceptedRequests(lessor.getId())));
		
	
		
		
		result.addObject("requestURI", "/contest/display.do");
		return result;
	}
	
	
	
	
	protected ModelAndView createDashboardModelAndView(Double avgAcceptedRequestLessor,Double avgDeniedRequestLessor,
			Double avgDeniedRequestTenant,Double avgAcceptedRequestTenant,Collection<Lessor> lessorApprovedMoreRequest,
			Collection<Lessor> lessorDeniedMoreRequest,Collection<Lessor> lessorPendingMoreRequest
			,Collection<Tenant> tenantPendingMoreRequest,Collection<Tenant> tenantApprovedMoreRequest,
			Collection<Tenant> tenantDeniedMoreRequest,Collection<Lessor> lessorRatioMax,Collection<Lessor> lessorRatioMin
			,Collection<Tenant> tenantRatioMax,Collection<Tenant> tenantRatioMin,Integer minResultsPerFinder,Integer maxResultsPerFinder,Double avgResultsPerFinder,
			Double minAuditPerProperty,Double maxAuditPerProperty,Double avgAuditPerProperty,
			Collection<Attribute> attributeDescribePropertyDesc,Double minSocialIdentityPerActor
			,Double maxSocialIdentityPerActor,Double avgSocialIdentityPerActor,Integer maxInvoicePerTenant,
			Integer minInvoicePerTenant,Double avgInvoicePerTenant,Double invoicesAmount,
			Double avgRequestPerPropertyWithAudits,Double avgRequestPerPropertyWithoutAudits){
		ModelAndView result;
		
		result = new ModelAndView("actor/administrator/dashboard");
		
		
		result.addObject("avgAcceptedRequestLessor",avgAcceptedRequestLessor);
		result.addObject("avgDeniedRequestLessor",avgDeniedRequestLessor);
		result.addObject("avgDeniedRequestTenant",avgDeniedRequestTenant);
		result.addObject("avgAcceptedRequestTenant",avgAcceptedRequestTenant);
		result.addObject("lessorApprovedMoreRequest",lessorApprovedMoreRequest);
		result.addObject("lessorDeniedMoreRequest",lessorDeniedMoreRequest);
		result.addObject("lessorPendingMoreRequest",lessorPendingMoreRequest);
		result.addObject("tenantPendingMoreRequest",tenantPendingMoreRequest);
		result.addObject("tenantApprovedMoreRequest",tenantApprovedMoreRequest);
		result.addObject("tenantDeniedMoreRequest",tenantDeniedMoreRequest);
		result.addObject("lessorRatioMax",lessorRatioMax);
		result.addObject("tenantRatioMin",tenantRatioMin);
		result.addObject("lessorRatioMin",lessorRatioMin);
		result.addObject("tenantRatioMax",tenantRatioMax);
		result.addObject("minResultsPerFinder",minResultsPerFinder);
		result.addObject("maxResultsPerFinder",maxResultsPerFinder);
		result.addObject("avgResultsPerFinder",avgResultsPerFinder);
		result.addObject("minAuditPerProperty",minAuditPerProperty);
		result.addObject("maxAuditPerProperty",maxAuditPerProperty);
		result.addObject("avgAuditPerProperty",avgAuditPerProperty);
		result.addObject("attributeDescribePropertyDesc",attributeDescribePropertyDesc);
		result.addObject("minSocialIdentityPerActor",minSocialIdentityPerActor);
		result.addObject("maxSocialIdentityPerActor",maxSocialIdentityPerActor);
		result.addObject("avgSocialIdentityPerActor",avgSocialIdentityPerActor);
		result.addObject("maxInvoicePerTenant",maxInvoicePerTenant);
		result.addObject("minInvoicePerTenant",minInvoicePerTenant);
		result.addObject("avgInvoicePerTenant",avgInvoicePerTenant);
		result.addObject("invoicesAmount",invoicesAmount);
		result.addObject("avgRequestPerPropertyWithAudits",avgRequestPerPropertyWithAudits);
		result.addObject("avgRequestPerPropertyWithoutAudits",avgRequestPerPropertyWithAudits);
		result.addObject("lessor",lessorService.findAll());
		
		return result;
	}
	private Collection<Lessor> devuelveMaxLessor(Collection<Object[]> col){
		long i=123456;
		List<Lessor> lessors=new ArrayList<Lessor>();
		for(Object[] o:col){
			if(i==123456){
				i=(long) o[1];
				lessors.add((Lessor) o[0]);
			}else{
				long e=(long) o[1];
				if(e>i){
					lessors.clear();
					lessors.add((Lessor) o[0]);
					i=e;
				}else if(e==i){
					lessors.add((Lessor) o[0]);
				}
			}
		}
		return lessors;
		
	}
	
	private Collection<Tenant> devuelveMaxTenant(Collection<Object[]> col){
		long i=123456;
		List<Tenant> lessors=new ArrayList<Tenant>();
		for(Object[] o:col){
			if(i==123456){
				i=(long) o[1];
				lessors.add((Tenant) o[0]);
			}else{
				long e=(long) o[1];
				if(e>i){
					lessors.clear();
					lessors.add((Tenant) o[0]);
					i=e;
				}else if(e==i){
					lessors.add((Tenant) o[0]);
				}
			}
		}
		return lessors;
		
	}
	
	private Collection<Attribute> devuelveAttributes(Collection<Object[]> col){
		List<Attribute> lessors=new ArrayList<Attribute>();
		for(Object[] o:col){
				lessors.add((Attribute) o[0]);
			
		}
		return lessors;
		
	}
	private Collection<Property> devuelveProperties(Collection<Object[]> col){
		System.out.println();
		
		List<Property> property=new ArrayList<Property>();
		for(Object[] o:col){
			property.add((Property) o[0]);
				
		}
		return property;
		
	}

}
