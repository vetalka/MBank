package mainClasses;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class AdminAction extends Action implements IAdminActions {

	
	// add new client and account and activity like a first deposit 
	//get of the commission from the balance also calculate the balance off
	//the account after commission. States credit limit and client type
	/* (non-Javadoc)
	 * @see mainClasses.IAdminActions#addClientAdmin(mainClasses.Client, mainClasses.Accounts)
	 */
	@Override
	public void addClientAdmin(Client clientBean , Accounts accountBean ) throws CheckedExceptions, UncheckedRuntimeException {
		
		
		Map <String , String> prop = dbManagers.getProperties();
		
		Calendar acitityDate = Calendar.getInstance();
	    Date c = new Date(acitityDate.getTimeInMillis());

		accountBean.getBalance();
		clientBean.getClientId();
		
		
		Activity activityBean = new Activity();
		
		activityBean.setAmount(accountBean.getBalance());
		activityBean.setActivityDate(c);
		activityBean.setDescription("First Deposit");
		activityBean.setCommission(Double.parseDouble(prop.get("commission_rate")));
		accountBean.setComment("First Deposite");
		
        if (accountBean.getBalance() <= 10000 ){
			
			accountBean.setCreditLimit(prop.get("regular_credit_limit"));
			clientBean.setClientType(ClientType.REGULAR);
			
		}else if (accountBean.getBalance() <= 100000 && accountBean.getBalance() > 10000){
			
			accountBean.setCreditLimit(prop.get("gold_credit_limit"));
			clientBean.setClientType(ClientType.GOLD);
			
		}else if(accountBean.getBalance() > 100000){
			
			accountBean.setCreditLimit(prop.get("platinum_credit_limit"));
			clientBean.setClientType(ClientType.PLATINUM);
		}
		
        accountBean.setBalance(accountBean.getBalance()-activityBean.getCommission());
        
        if (clientBean.getClientName().equals(null) || clientBean.getClientName().equals("") || clientBean.getClientType().equals(null)
        		|| clientBean.getClientType().equals("") || clientBean.getComment().equals(null) || clientBean.getComment().equals("")
        		|| clientBean.getEmail().equals(null) || clientBean.getEmail().equals("") || clientBean.getPassword().equals(null)
        		|| clientBean.getPassword().equals("") || clientBean.getPhone().equals(null) || clientBean.getPhone().equals("")
        		|| clientBean.getAddress().equals(null) || clientBean.getAddress().equals("")){
        
        	throw new UncheckedRuntimeException("One of Client column is wmpty!");
        	
        }else{
        
        dbManagers.addClientAdmin(clientBean);
		dbManagers.addFirstActivity(activityBean);
        
        }
        
        if ( accountBean.getBalance() <= 0 || accountBean.getComment().equals(null) || accountBean.getComment().equals("")){
        	
        	throw new UncheckedRuntimeException("One of Account column is empty!");
        	
        }else{
        	
    		dbManagers.addAccounts(accountBean);

        }
	}
	
	// Admin can to update client bean when you update the client type 
	// this is atomaticly updating a Account credit limit in actualy client 
	/* (non-Javadoc)
	 * @see mainClasses.IAdminActions#UpdateClientAdmin(long, mainClasses.ClientType, java.lang.String)
	 */
	@Override
	public void UpdateClientAdmin (long clientId ,ClientType clientType , String comment ) throws CheckedExceptions, UncheckedRuntimeException{
		
		if (clientId <= 0 || clientType.equals(null) || clientType.equals("") || comment.equals(null) || comment.equals("")){
			
			throw new UncheckedRuntimeException("One of column is empty!");
			
		}else{
			
		dbManagers.updateClientAdmin( clientId , clientType , comment);
		
		}
		Map <String , String> prop = dbManagers.getProperties();
		
        Client client = dbManagers.getClientBean(clientId);
        
        ArrayList<Accounts> account = dbManagers.ViewAccountsByClientId(clientId);
		
		for(Accounts accountBean : account){
			
		if(client.getClientType().equals(ClientType.REGULAR)){

			accountBean.setCreditLimit(prop.get("regular_credit_limit"));

		}else if(client.getClientType().equals(ClientType.GOLD)){
			
			accountBean.setCreditLimit(prop.get("gold_credit_limit"));
				
		}else if (client.getClientType().equals(ClientType.PLATINUM)){
			
			accountBean.setCreditLimit(prop.get("platinum_credit_limit"));
			
		}
		
		dbManagers.updateClientAdmin(accountBean);
		
		}
	}
	
	//remove all client properties (all Account , all deposits and all Activities)
	/* (non-Javadoc)
	 * @see mainClasses.IAdminActions#RemoveClient(long)
	 */
	@Override
	public void RemoveClient(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
		
		ArrayList<Deposits> deposit = dbManagers.ViewDepositsByClientId(clientId);
		
		for( Deposits depositsBean : deposit){
			
			
			Map <String , String> prop = dbManagers.getProperties();

	    	Accounts accountBean = dbManagers.getAccountByClientId(clientId);
	    	
	    	Client clientBean = dbManagers.getClientBean(clientId);
	    	
	    	Calendar closingDate = Calendar.getInstance();
			closingDate.setTimeInMillis(depositsBean.getClosingDate().getTime());
	    	
			
			Calendar openingDate = Calendar.getInstance();
		    Date o = new Date(openingDate.getTimeInMillis());
			depositsBean.setOpeningDate(o);
			openingDate.setTimeInMillis(depositsBean.getOpeningDate().getTime());
			
			Activity activityBean = new Activity(clientId , depositsBean.getBalance());
			
		
				 if(clientBean.getClientType().equals("REGULAR")){			
									
						activityBean.setCommission(Double.parseDouble(prop.get("regular_daily_interest")));
						depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(activityBean.getCommission())*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
						activityBean.setCommission(Double.parseDouble(prop.get("regular_deposite_commission")));
						depositsBean.setBalance(depositsBean.getBalance()-(depositsBean.getBalance()*(activityBean.getCommission())));
							    	
					    	depositsBean.setDepositType(DepositType.LongDeposit);
					    	activityBean.setDescription("regular_deposite_commission" + "%");
							activityBean.setCommission(Double.parseDouble(prop.get("regular_deposite_commission")));
					    
					}else if(clientBean.getClientType().equals("GOLD")){
						
						activityBean.setCommission(Double.parseDouble(prop.get("gold_daily_interest")));
						depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(activityBean.getCommission())*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
						activityBean.setCommission(Double.parseDouble(prop.get("gold_deposite_commission")));
						depositsBean.setBalance(depositsBean.getBalance()-(depositsBean.getBalance()*(activityBean.getCommission())));

					    	    depositsBean.setDepositType(DepositType.LongDeposit);
					    	    activityBean.setDescription("gold_deposite_commission" + "%");
					    	    activityBean.setCommission(Double.parseDouble(prop.get("gold_deposite_commission")));
					    	    
					}else if(clientBean.getClientType().equals("PLATINUM")){
						
						activityBean.setCommission(Double.parseDouble(prop.get("platinum_daily_interest")));
						depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(0.21)*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
						activityBean.setCommission(Double.parseDouble(prop.get("platinum_deposite_commission")));
						depositsBean.setBalance(depositsBean.getBalance()-(depositsBean.getBalance()*(activityBean.getCommission())));

					    	        depositsBean.setDepositType(DepositType.LongDeposit);
					    	        activityBean.setDescription("platinum_deposite_commission" + "%");
					    			activityBean.setCommission(Double.parseDouble(prop.get("platinum_deposite_commission")));
					    			
					}    
			 
			 
			 activityBean.setCommission(Double.parseDouble(prop.get("pre_open_fee")));
			 activityBean.setDescription("pre_open_fee");
			 
			 accountBean.setBalance(accountBean.getBalance()+(depositsBean.getEstimatedBalance())-((depositsBean.getEstimatedBalance())*(activityBean.getCommission())));
	         accountBean.setComment("pre_open_fee");
			 activityBean.setActivityDate(o);
			 activityBean.setAmount((depositsBean.getEstimatedBalance())-((depositsBean.getEstimatedBalance())*(activityBean.getCommission())));
	         
	         
	         if (accountBean.getBalance() <= 10000 ){
	  			
	  			accountBean.setCreditLimit(prop.get("regular_credit_limit"));
	  			clientBean.setClientType(ClientType.REGULAR);
	  			
	  		}else if (accountBean.getBalance() <= 100000 && accountBean.getBalance() > 10000){
	  			
	  			accountBean.setCreditLimit(prop.get("gold_credit_limit"));
	  			clientBean.setClientType(ClientType.GOLD);
	  			
	  		}else if(accountBean.getBalance() > 100000){
	  			
	  			accountBean.setCreditLimit(prop.get("platinum_credit_limit"));
	  			clientBean.setClientType(ClientType.PLATINUM);
	  			
	  		}
			
	      
	         dbManagers.preOpenDeposite(depositsBean.getDepositId());
		}
		
		dbManagers.removeActivityByClientId(clientId);
		dbManagers.removeClient(clientId);
		dbManagers.removeAccountByClientId(clientId);
		
	}
	
	//you can chose a Client by his id add a system will show you all his details (Accounts , Activity and Deposits) 
    /* (non-Javadoc)
	 * @see mainClasses.IAdminActions#ViewClientDetails(long)
	 */
    @Override
	public Client ViewClientDetails(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
    	    
    	System.out.println("Client Details");
    	System.out.println();
    	Client client = dbManagers.getClientBean(clientId);
    	
    	
    	
    	
    	return client;
    	
    }
    
    //you can get only one Activity detail by his id
    /* (non-Javadoc)
	 * @see mainClasses.IAdminActions#ViewActivitiesDetails(long)
	 */
    @Override
	public Activity ViewActivitiesDetails(long id) throws CheckedExceptions, UncheckedRuntimeException{

    	Activity activity = dbManagers.getActivityBean(id);
    	
    	
    	return activity;
    }
	
    //you can see Account details by account id
    /* (non-Javadoc)
	 * @see mainClasses.IAdminActions#ViewAccountDetails(long)
	 */
    @Override
	public Accounts ViewAccountDetails(long accountId) throws CheckedExceptions, UncheckedRuntimeException{
    	
    	System.out.println("Account Details");
    	Accounts account = dbManagers.getAccountsBean(accountId);
    	
    	return account;
    	
    }
    
    //you can see Deposit details by deposit id 
    /* (non-Javadoc)
	 * @see mainClasses.IAdminActions#ViewDepositsDetails(long)
	 */
    @Override
	public Deposits ViewDepositsDetails(long depositeId) throws CheckedExceptions, UncheckedRuntimeException{

    	Deposits deposit = dbManagers.getDepositeBean(depositeId);
    	
    	return deposit;
    }

    //you can see all client in the bank system 
    /* (non-Javadoc)
	 * @see mainClasses.IAdminActions#ViewAllClientDetails()
	 */
    @Override
	public ArrayList<Client> ViewAllClientDetails() throws CheckedExceptions, UncheckedRuntimeException {
    	
    	ArrayList<Client> client = dbManagers.ViewAllClientDetails();
    	
    	
    	
    	return client;
    }
    
    //you can see all Account is bank system
    /* (non-Javadoc)
	 * @see mainClasses.IAdminActions#ViewAllAccountDetails()
	 */
    @Override
	public ArrayList<Accounts> ViewAllAccountDetails() throws CheckedExceptions, UncheckedRuntimeException{
    	
    	ArrayList<Accounts> account = dbManagers.ViewAllAccountDetails();
    	
    	return account;
    }
    
    //you can see all deposits in a bank system
    /* (non-Javadoc)
	 * @see mainClasses.IAdminActions#ViewAllDepositsDetails()
	 */
    @Override
	public ArrayList<Deposits> ViewAllDepositsDetails() throws CheckedExceptions, UncheckedRuntimeException{
    	
    	ArrayList<Deposits> deposit = dbManagers.ViewAllDepositsDetails();
        
    	for (Deposits depositBean : deposit){
    		
    		System.out.println(depositBean);
    		
    	}
    	
    	return deposit;
    }

    //you can see all activities in the bank system
	/* (non-Javadoc)
	 * @see mainClasses.IAdminActions#ViewAllActivitiesDetails()
	 */
	@Override
	public ArrayList<Activity> ViewAllActivitiesDetails() throws CheckedExceptions, UncheckedRuntimeException{
		
		ArrayList<Activity> activity = dbManagers.ViewAllActivitiesDetails();
		
		
		return activity;
		
	}
	
	//you can see all properties in the bank system
	/* (non-Javadoc)
	 * @see mainClasses.IAdminActions#ViewSystemProperty()
	 */
	@Override
	public Map<String, String> ViewSystemPropertyAdmin() throws CheckedExceptions, UncheckedRuntimeException{

   	 Map <String , String> prop = dbManagers.getProperties();
	 
 

   	 return prop;
      }
	
	//you can update all system properties by propKey
	/* (non-Javadoc)
	 * @see mainClasses.IAdminActions#UpdateSystemProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public void UpdateSystemProperty(String propKey , String propValue) throws CheckedExceptions, UncheckedRuntimeException{
		
		if (propKey.equals(null) || propKey.equals("") || propValue.equals(null) || propValue.equals("")){
		
			throw new UncheckedRuntimeException("One of column is empty!");
			
		}else{
		
		dbManagers.updateSystemProperty(propKey, propValue);
		
		}
	}
		
	
	//you can remove Account by accountId and also that give a deskripshion in Activity table
	/* (non-Javadoc)
	 * @see mainClasses.IAdminActions#RemoveAccount(long)
	 */
	@Override
	public void RemoveAccount(long accountId) throws CheckedExceptions, UncheckedRuntimeException{
		
		Accounts accountBean = dbManagers.getAccountsBean(accountId);
		
		Activity activityBean = new Activity(accountBean.getClientId());
		
		Calendar acitityDate = Calendar.getInstance();
		Date c = new Date(acitityDate.getTimeInMillis());
		
		activityBean.setActivityDate(c);

		if(accountBean.getBalance()<0){
			
			activityBean.setDescription("Commission charged due to negative balance account on client removal");
			activityBean.setAmount(-(accountBean.getBalance()));
			activityBean.setCommission(accountBean.getBalance());
			
			dbManagers.CrateNewDeposit(activityBean);
		}
		
		activityBean.setDescription("Delete Account");
		dbManagers.addActivity(activityBean, accountBean.getClientId());
		dbManagers.removeAccounts(accountId);
	}
	
	
}
