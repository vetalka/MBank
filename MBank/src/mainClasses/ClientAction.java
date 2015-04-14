package mainClasses;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IClientActions;

public class ClientAction extends Action implements IClientActions {

	
	// update to client a newAddress newEmail and newPhone by his client id
    /* (non-Javadoc)
	 * @see mainClasses.IClientActions#updateClient(long, java.lang.String, java.lang.String, java.lang.String)
	 */
    @Override
	public void updateClient (long clientId, String newAddress,
	    String newEmail, String newPhone) throws CheckedExceptions, UncheckedRuntimeException{
	   	
    	if ((newAddress.equals(null) || newAddress.equals("")) || (newEmail.equals(null) || newEmail.equals("")) || (newPhone.equals(null) || newPhone.equals(""))){
    		
    		throw new UncheckedRuntimeException("One of columns is emply!");
    			
    	}else{
    	
    	dbManagers.updateClient(clientId, newAddress, newEmail, newPhone);
    	
    	}
	}
    
    //Created a new deposit with calculate the type long or short and get off 
    //a commission by the client type and insert this to activity off this client
    /* (non-Javadoc)
	 * @see mainClasses.IClientActions#CrateNewDeposit(mainClasses.Deposits, long)
	 */
    @Override
	public void CrateNewDeposit(Deposits depositsBean , long clientId ) throws CheckedExceptions, UncheckedRuntimeException{
    	
    	Map <String , String> prop = dbManagers.getProperties();
    	Client client = dbManagers.getClientBean(clientId);   	
    	
		Activity activityBean = new Activity(clientId , depositsBean.getBalance());
				
		Calendar openingDate = Calendar.getInstance();
		openingDate.setTimeInMillis(depositsBean.getOpeningDate().getTime());
		Calendar closingDate = Calendar.getInstance();
		closingDate.setTimeInMillis(depositsBean.getClosingDate().getTime());
 
		depositsBean.setClientid(clientId);
		depositsBean.getOpeningDate();
		depositsBean.getClosingDate();
		depositsBean.getBalance();
		
		if(client.getClientType().equals(ClientType.REGULAR)){			
				
			activityBean.setCommission(Double.parseDouble(prop.get("regular_daily_interest")));
			depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(activityBean.getCommission())*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
			
		    if((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR)) > 1){
		    	
		    	depositsBean.setDepositType(DepositType.LongDeposit);
		    	activityBean.setDescription("regular_deposite_commission" + "%");
				activityBean.setCommission(Double.parseDouble(prop.get("regular_deposite_commission")));
			
			}else {
				
				depositsBean.setDepositType(DepositType.ShortDeposit);
				activityBean.setDescription("regular_deposite_commission" + "%");
				activityBean.setCommission(Double.parseDouble(prop.get("regular_deposite_commission")));
				
			}
		    
		}else if(client.getClientType().equals(ClientType.GOLD)){
			
			activityBean.setCommission(Double.parseDouble(prop.get("gold_daily_interest")));
			depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(activityBean.getCommission())*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));

              if((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR)) > 1){
		    	
		    	    depositsBean.setDepositType(DepositType.LongDeposit);
		    	    activityBean.setDescription("gold_deposite_commission" + "%");
		    	    activityBean.setCommission(Double.parseDouble(prop.get("gold_deposite_commission")));
		    	    
			  }else {
				
				  depositsBean.setDepositType(DepositType.ShortDeposit);
				  activityBean.setDescription("gold_deposite_commission" + "%");
				  activityBean.setCommission(Double.parseDouble(prop.get("gold_deposite_commission")));				
			
		      } 
              
		}else if(client.getClientType().equals(ClientType.PLATINUM)){
			
			activityBean.setCommission(Double.parseDouble(prop.get("platinum_daily_interest")));
			depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(0.21)*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));

                   if((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR)) > 1){
		    	
		    	        depositsBean.setDepositType(DepositType.LongDeposit);
		    	        activityBean.setDescription("platinum_deposite_commission" + "%");
		    			activityBean.setCommission(Double.parseDouble(prop.get("platinum_deposite_commission")));
			
		       	   }else {
				
				        depositsBean.setDepositType(DepositType.ShortDeposit);
				        activityBean.setDescription("platinum_deposite_commission" + "%");
						activityBean.setCommission(Double.parseDouble(prop.get("platinum_deposite_commission")));
				
			       }
		       }    
		
		activityBean.setActivityDate(depositsBean.getOpeningDate());
		activityBean.setClientid(depositsBean.getClientId());
		
		if((depositsBean.getBalance()<= 0 ) || (depositsBean.getClosingDate().equals(null) || depositsBean.getClosingDate().equals("")) || (depositsBean. getClientId() == null) || (depositsBean.getOpeningDate().equals(null) || depositsBean.getOpeningDate().equals(""))){
			
    		throw new UncheckedRuntimeException("One of columns is emply!");

		}else{
		
		dbManagers.CrateNewDeposit(depositsBean);
		dbManagers.CrateNewDeposit(activityBean);
		
		}
		}
    
    //doing deposit and update credit limit and client type 
    /* (non-Javadoc)
	 * @see mainClasses.IClientActions#DepositToAccount(long, double)
	 */
    @Override
	public void DepositToAccount(long clientId , double amount) throws CheckedExceptions, UncheckedRuntimeException{
    	
    	Map <String , String> prop = dbManagers.getProperties();
    	//Map <Long , Double> account = dbManagers.getAccount();
    
    	Accounts accountBean = dbManagers.getAccountByClientId(clientId);

    	
    	Calendar openingDate = Calendar.getInstance();
		Date o = new Date(openingDate.getTimeInMillis());

		//Accounts accountBean = new Accounts(account.get(clientId));
		
		accountBean.setAccountId(clientId);
		
    	Activity activityBean = new Activity(clientId , amount);
    	Client clientBean = new Client(clientId);
    	
    	activityBean.setActivityDate(o);
    	activityBean.setCommission(Double.parseDouble(prop.get("commission_rate")));
    	activityBean.setDescription("Deposit");
    	accountBean.setComment("Deposit");
    	accountBean.setBalance(accountBean.getBalance()-activityBean.getCommission()+activityBean.getAmount());
    	
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

        if (clientId <= 0 || amount <= 0 ){
       
        	throw new UncheckedRuntimeException("You nedd to deposit some money !");
        	
        }else {
        
    	dbManagers.addActivity(activityBean , clientId);
    	dbManagers.DepositToAccount(accountBean);
    	dbManagers.DepositToAccount(clientBean);
    	
        }
    }
    
    
    //withdraw from account and update credit limit and client type
    //if a balance of this account is minus its throws exsepshion 
    /* (non-Javadoc)
	 * @see mainClasses.IClientActions#WithdrawFromAccount(long, double)
	 */
    @Override
	public void WithdrawFromAccount(long clientId , double amount) throws CheckedExceptions, UncheckedRuntimeException{
    
    	Map <String , String> prop = dbManagers.getProperties();
    	//Map <Long , Double> account = dbManagers.getAccount();
    	
    	Accounts accountBean = dbManagers.getAccountByClientId(clientId);
    	
    	Calendar openingDate = Calendar.getInstance();
		java.sql.Date o = new Date(openingDate.getTimeInMillis());

		//Accounts accountBean = new Accounts(account.get(clientId));
		
		accountBean.setAccountId(clientId);
		
    	Activity activityBean = new Activity(clientId , amount);
    	Client clientBean = new Client(clientId);
    	
    	activityBean.setActivityDate(o);
    	activityBean.setCommission(Double.parseDouble(prop.get("commission_rate")));
    	activityBean.setDescription("Withdraw");
    	accountBean.setComment("Withdraw");
    	

    	
    		
    	if ((accountBean.getCreditLimit().equals(prop.get("regular_credit_limit"))) 
    			|| (accountBean.getCreditLimit().equals(prop.get("gold_credit_limit"))) 
    			|| (!accountBean.getCreditLimit().equals(prop.get("platinum_credit_limit")))){
    		
         	throw new UncheckedRuntimeException("You do not have enough money in the account! Please try again !.");

         	
    	
        }else if ((accountBean.getCreditLimit().equals(prop.get("regular_credit_limit"))) 
    			|| (accountBean.getCreditLimit().equals(prop.get("gold_credit_limit"))) 
    			|| (accountBean.getCreditLimit().equals(prop.get("platinum_credit_limit")))){
        	
    		accountBean.setBalance(accountBean.getBalance()-activityBean.getCommission()-activityBean.getAmount());
    	
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
    	 
    	 if (clientId <= 0 || amount <= 0){
    		 
    		 throw new UncheckedRuntimeException("Enter a logical amount of money !");
    		 
    	 }else{
    		 
    	 dbManagers.addActivity(activityBean , clientId);
     	 dbManagers.DepositToAccount(accountBean);
     	 dbManagers.DepositToAccount(clientBean);
     	 
    	 }
    	}
    	
    }
      
    //pre opened the deposit and set credit limit and client type
    /* (non-Javadoc)
	 * @see mainClasses.IClientActions#PreOpenDeposite(long, long)
	 */
    @Override
	public void PreOpenDeposite(long clientId , long depositId) throws CheckedExceptions, UncheckedRuntimeException{
    	
    	Map <String , String> prop = dbManagers.getProperties();

    	Accounts accountBean = dbManagers.getAccountsBean(clientId);
    	
    	Client clientBean = dbManagers.getClientBean(clientId);
    	
    	Deposits depositsBean = dbManagers.getDepositeBean(clientId); 
    	
    	Calendar closingDate = Calendar.getInstance();
		closingDate.setTimeInMillis(depositsBean.getClosingDate().getTime());
    	
		
		Calendar openingDate = Calendar.getInstance();
		Date open = new Date(openingDate.getTimeInMillis());
		depositsBean.setOpeningDate(open);
		openingDate.setTimeInMillis(depositsBean.getOpeningDate().getTime());
		
		Activity activityBean = new Activity(clientId , depositsBean.getBalance());
		
		 if((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR)) < 1){
		//need to get exsepsion	 
			 
			 throw new CheckedExceptions("You cant do it!");
		
			 
		 }else if((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR)) > 1){
			 
			 if(clientBean.getClientType().equals(ClientType.REGULAR)){			
								
					activityBean.setCommission(Double.parseDouble(prop.get("regular_daily_interest")));
					depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(activityBean.getCommission())*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
					activityBean.setCommission(Double.parseDouble(prop.get("regular_deposite_commission")));
					depositsBean.setBalance(depositsBean.getBalance()-(depositsBean.getBalance()*(activityBean.getCommission())));
						    	
				    	depositsBean.setDepositType(DepositType.LongDeposit);
				    	activityBean.setDescription("regular_deposite_commission" + "%");
						activityBean.setCommission(Double.parseDouble(prop.get("regular_deposite_commission")));
				    
				}else if(clientBean.getClientType().equals(ClientType.GOLD)){
					
					activityBean.setCommission(Double.parseDouble(prop.get("gold_daily_interest")));
					depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(activityBean.getCommission())*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
					activityBean.setCommission(Double.parseDouble(prop.get("gold_deposite_commission")));
					depositsBean.setBalance(depositsBean.getBalance()-(depositsBean.getBalance()*(activityBean.getCommission())));

				    	    depositsBean.setDepositType(DepositType.LongDeposit);
				    	    activityBean.setDescription("gold_deposite_commission" + "%");
				    	    activityBean.setCommission(Double.parseDouble(prop.get("gold_deposite_commission")));
				    	    
				}else if(clientBean.getClientType().equals(ClientType.PLATINUM)){
					
					activityBean.setCommission(Double.parseDouble(prop.get("platinum_daily_interest")));
					depositsBean.setEstimatedBalance((depositsBean.getBalance())+depositsBean.getBalance()*(0.21)*((closingDate.get(Calendar.YEAR))-(openingDate.get(Calendar.YEAR))));
					activityBean.setCommission(Double.parseDouble(prop.get("platinum_deposite_commission")));
					depositsBean.setBalance(depositsBean.getBalance()-(depositsBean.getBalance()*(activityBean.getCommission())));

				    	        depositsBean.setDepositType(DepositType.LongDeposit);
				    	        activityBean.setDescription("platinum_deposite_commission" + "%");
				    			activityBean.setCommission(Double.parseDouble(prop.get("platinum_deposite_commission")));
				    			
				       } 
			 
			 
			 dbManagers.preOpenDeposite(depositId);
		
		 
		 activityBean.setCommission(Double.parseDouble(prop.get("pre_open_fee")));
		 activityBean.setDescription("pre_open_fee");
		 
		 accountBean.setBalance(accountBean.getBalance()+(depositsBean.getEstimatedBalance())-((depositsBean.getEstimatedBalance())*(activityBean.getCommission())));
         accountBean.setComment("pre_open_fee");
		 activityBean.setActivityDate(open);
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
          
		 dbManagers.addActivity(activityBean, clientId);
		 dbManagers.updateAccountBean(accountBean);
		 dbManagers.DepositToAccount(clientBean);
	
		 }
		 
    	}
   	
    //view all details of this client (Account , Activity , Deposit)
    /* (non-Javadoc)
	 * @see mainClasses.IClientActions#ViewClientDetails(long)
	 */
    @Override
	public Client ViewClientDetailsClient(long clientId) throws CheckedExceptions, UncheckedRuntimeException{
    	    	    
    	System.out.println("Client Details");
    	System.out.println();
    	Client client = dbManagers.getClientBean(clientId);
    	
    	return client;
        }
    
     //view Account details by accountId
     /* (non-Javadoc)
	 * @see mainClasses.IClientActions#ViewAccountDetails(long)
	 */
    @Override
	public Accounts ViewAccountDetailsClient(long accountId) throws CheckedExceptions, UncheckedRuntimeException{
    	
    	System.out.println("Account Details");
     	System.out.println();
     	Accounts account = dbManagers.getAccountsBean(accountId);
     	
     	return account;
    		
    }
     
     //view Deposit details by deposit Id
     /* (non-Javadoc)
	 * @see mainClasses.IClientActions#ViewDepositsDetails(long)
	 */
    @Override
	public Deposits ViewDepositsDetailsClient(long depositeId) throws CheckedExceptions, UncheckedRuntimeException{

    	 Deposits deposit = dbManagers.getDepositeBean(depositeId);
     	
     	return deposit;
     	
     }
     
     //View Activity details by activity Id
     /* (non-Javadoc)
	 * @see mainClasses.IClientActions#ViewActivitiesDetails(long)
	 */
    @Override
	public Activity ViewActivitiesDetailsClient(long id) throws CheckedExceptions, UncheckedRuntimeException {

    	 Activity activity = dbManagers.getActivityBean(id);
     	     	
     	return activity;
     	
     }
     
     //view all system properties
     /* (non-Javadoc)
	 * @see mainClasses.IClientActions#ViewSystemProperty()
	 */
    @Override
	public Map<String, String> ViewSystemProperty() throws CheckedExceptions, UncheckedRuntimeException{

    	 Map <String , String> prop = dbManagers.getProperties();
    	     	 
    	 return prop;
      }
    
   
    
}
