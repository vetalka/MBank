package mainClasses;

import java.sql.Date;

public class Activity {
	
	@Override
	public String toString() {
		return "Activity [id=" + id + ", amount=" + amount + ", activityDate="
				+ activityDate + ", commission=" + commission
				+ ", description=" + description + ", clientId=" + clientId
				+ "]";
	}

	private Long id;
	private double amount;
    Date activityDate;
    private double commission;
    private String description;
    //private Accounts accounts;
    private Long clientId;
    
	public Activity(Long clientId, double amount) {
		
		this.clientId = clientId;
		this.amount = amount;
		//this.activityDate = activityDate;
		//this.commission = commission;
		//this.description = description;
		//this.firstAmount = accounts.getBalance();
		
	}

	public Activity(){
		
	}
	
	public Activity(long clientId){
		
		this.clientId = clientId;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientid() {
		return clientId;
	}

	public void setClientid(Long clientId) {
		this.clientId = clientId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
	
		this.activityDate = activityDate;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
