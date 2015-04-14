package mainClasses;

public class Accounts  {

	
	

	private Long accountId  ;
	private Long clientId;
	private double balance;
	private String creditLimit;
	private String comment;
	
	public Accounts(){
		
		
	}
	
	public Accounts(  double balance ) {
		
		this.balance = balance;
	}
	
	public Accounts( long accountId , String creditLimit ) {
		
		this.accountId = accountId;
		this.creditLimit = creditLimit;
	}
	

    public Long getClientId(){
    	
    	return clientId;
    	
    }
    
   public void setClientId(Long clientId ){
		
		this.clientId= clientId;
		
	}
    
	public Long getAccountId( ) {
		
		return accountId;
	}

	public void setAccountId(Long accountId ){
		
		this.accountId= accountId;
		
	}
	
	public double getBalance() {
		
		return balance;
		
	}

	public void setBalance(double balance) {
		
		this.balance = balance;
		
	}

	public String getComment() {
		
		return comment;
		
	}

	public void setComment(String comment) {
		
		this.comment = comment;
		
	}
		
    public String getCreditLimit() {
		
		return creditLimit;
		
	}
    
	public void setCreditLimit(String creditLimit) {
		
		this.creditLimit = creditLimit;
	}
	
	@Override
	public String toString() {
		
		return "Accounts [accountId=" + accountId + ", clientId=" + clientId
				+ ", balance=" + balance + ", creditLimit=" + creditLimit
				+ ", comment=" + comment + "]";
	}

}
