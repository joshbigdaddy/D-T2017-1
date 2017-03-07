package forms;


import domain.Audit;


public class EditAuditForm  {

    private Integer propertyId;
    private Audit audit;
	public Integer getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	public Audit getAudit() {
		return audit;
	}
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

  
}
