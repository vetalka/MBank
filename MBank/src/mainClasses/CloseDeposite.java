package mainClasses;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;

public class CloseDeposite implements Runnable   {

 
	private Managers dbManagers = null;
	
	private Map <String , String> prop = null;
	private Client clientBean = null;
    private Accounts accountBean = null;
    private Activity activityBean = null;
    private boolean finalized = false;
    
    
	public void Initialize(){
		
		
	   	 dbManagers = Managers.getInstance();

	   	 finalized = true;
	 
	}
	
	public void getDeposit() throws CheckedExceptions, UncheckedRuntimeException {
		
		ArrayList<Deposits> deposit = dbManagers.ViewAllDepositsDetails();
		
		for ( Deposits depositBean : deposit){
			
		    checkDate(depositBean);
		    
		}
		
	}
	
	public void getOperators(Deposits depositBean) throws CheckedExceptions, UncheckedRuntimeException{
		
	    prop = dbManagers.getProperties();
		clientBean = dbManagers.getClientBean(depositBean.getClientId());
		accountBean = dbManagers.getAccountByClientId(depositBean.getClientId());
		
	}
	
	public void checkDate(Deposits depositBean) throws CheckedExceptions, UncheckedRuntimeException{

		getOperators(depositBean);
		
		Calendar closingDate = Calendar.getInstance();
		closingDate.setTimeInMillis(depositBean.getClosingDate().getTime());
    	
		Calendar openingDate = Calendar.getInstance();
		Date open = new Date(openingDate.getTimeInMillis());
		depositBean.setOpeningDate(open);
		openingDate.setTimeInMillis(depositBean.getOpeningDate().getTime());
		
		
		
		if (((closingDate.get(Calendar.YEAR)) - (openingDate.get(Calendar.YEAR)) == 0 )
				&& ((closingDate.get(Calendar.MONTH)) - (openingDate.get(Calendar.MONTH)) == 0) 
				&& ((closingDate.get(Calendar.DAY_OF_MONTH)) - (openingDate.get(Calendar.DAY_OF_MONTH))) == 0){

			updateClientAndAccount(accountBean , clientBean , depositBean );

		}
	}
	
	public void updateClientAndAccount(Accounts accountBean , Client clientBean , Deposits depositBean) throws CheckedExceptions, UncheckedRuntimeException{
		
		getOperators(depositBean);
		
		accountBean.setBalance(accountBean.getBalance()+depositBean.getEstimatedBalance());
		
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
		
		returnToDataBase(depositBean , accountBean , clientBean );
		ActivityDescripshion(depositBean);
	}
	
	public void ActivityDescripshion(Deposits depositBean) throws CheckedExceptions, UncheckedRuntimeException {
		
		activityBean = new Activity();
		
		activityBean.setActivityDate(depositBean.getClosingDate());
		activityBean.setDescription("Close deposit automaticly");
		activityBean.setAmount(depositBean.getEstimatedBalance());
		activityBean.setCommission(0);
		activityBean.setClientid(depositBean.getClientId());
		
		
		dbManagers.addActivity(activityBean, depositBean.getClientId());
		
	}
	
	
	public void returnToDataBase(Deposits depositBean , Accounts accountBean , Client clientBean) throws CheckedExceptions, UncheckedRuntimeException{
		
		dbManagers.preOpenDeposite(depositBean.getDepositId());
		dbManagers.updateAccountBean(accountBean);
		dbManagers.DepositToAccount(clientBean);
		
	}
	
	@Override 
	public void run(){
		
		Initialize();
		
		try {
			
			while (finalized){
				
			getDeposit();
				
			Thread.sleep(3600);
			
			}
		} catch (InterruptedException | CheckedExceptions | UncheckedRuntimeException e) {
			e.printStackTrace();
		}
	}
}
