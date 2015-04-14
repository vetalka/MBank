package mainClasses;

public class Client  {
	
	private Long  clientId;
	private String clientName;
	private String password;
    private ClientType clientType = null;
	private String address;
	private String email;
	private String phone;
	private String comment;
	private Accounts accounts;
	
	public Client( String clientName, String password,
		    String address, String email, String phone,
			String comment ) {
		
		this.clientName = clientName;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.comment = comment;
		
		
		
	}
	
	public Client( long clientId){
		
		this.clientId =clientId;
		
		
	}
	
	public Client() {
		
	}

	public Long getClientId() {
		
		return clientId;
		
	}
	
    public void setClientId(Long clientId ){
		
		this.clientId= clientId;
		
	}

	public String getClientName() {
		
		return clientName;
		
	}

    public void setClientName(String clientName) {
    	
		this.clientName = clientName;
		
	}

	public String getPassword() {
		
		return password;
		
	}

	public void setPassword(String password) {
		
		this.password = password;
		
	}
    public ClientType getClientType() {
		
		return this.clientType;
		
	}

	public void setClientType( ClientType clientType ) {
		
		this.clientType = clientType;
		
	}

	public String getAddress() {
		
		return address;
		
	}

	public void setAddress(String address) {
		
		this.address = address;
		
	}

	public String getEmail() {
		
		return email;
		
	}

	public void setEmail(String email) {
		
		this.email = email;
		
	}

	public String getPhone() {
		
		return phone;
		
	}

	public void setPhone(String phone) {
		
		this.phone = phone;
		
	}

	public String getComment() {
		
		return comment;
		
	}

	public void setComment(String comment) {
		
		this.comment = comment;
		
	}

	public Accounts getAccounts() {
		
		return this.accounts;
		
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName
				+ ", password=" + password + ", clientType=" + clientType
				+ ", address=" + address + ", email=" + email + ", phone="
				+ phone + ", comment=" + comment + "]";
	}


}
