package mainClasses;

import java.sql.Date;

public class Deposits {
	
	@Override
	public String toString() {
		return "Deposits [depositId=" + depositId + ", clientId=" + clientId
				+ ", balance=" + balance + ", depositType=" + depositType
				+ ", estimatedBalance=" + estimatedBalance + ", openingDate="
				+ openingDate + ", closingDate=" + closingDate + "]";
	}

	private Long depositId;
    private Long clientId;
	private double balance;
	private DepositType depositType;
	private double estimatedBalance;
    Date openingDate;
	Date closingDate;
	
	public Deposits( Long  clientId, double balance,Date openingDate, Date closingDate) {
		 
		this.clientId = clientId;
		this.balance = balance;
		
		
		this.openingDate = openingDate;
		this.closingDate = closingDate;
		
	}

	public Deposits() {
		
	}
	
	public Long getDepositId() {
		return depositId;
	}

	public void setDepositId(Long depositId) {
		this.depositId = depositId;
	}

	public Long  getClientId() {
		return clientId;
	}

	public void setClientid(Long clientId) {
		this.clientId = clientId;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public DepositType getDepositType() {
		return depositType;
	}

	public void setDepositType(DepositType depositType) {
		this.depositType = depositType;
	}

	public double getEstimatedBalance() {
		return estimatedBalance;
	}

	public void setEstimatedBalance(double estimatedBalance) {
		this.estimatedBalance = estimatedBalance;
	}

	public Date getOpeningDate() {
		
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
	
				this.openingDate = openingDate; 
		
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		
		this.closingDate =  closingDate;
	
	}

	
}
