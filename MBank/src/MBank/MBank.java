package MBank;
import java.util.ArrayList;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;
import IMBank.IClientActions;
import Swing.AddClient;
import mainClasses.Accounts;
import mainClasses.Action;
import mainClasses.Activity;
import mainClasses.AdminAction;
import mainClasses.Client;
import mainClasses.ClientAction;
import mainClasses.ClientType;
import mainClasses.CloseDeposite;
import mainClasses.Deposits;
import mainClasses.Login;
import mainClasses.Managers;


public class MBank implements IClientActions, IAdminActions{



	private static MBank MBank = null;
	

	private Login login = null;
	
    private  IAdminActions adminAction = null;  
    private  IClientActions clientAction = null;
	
	private MBank(){
		
		login = new Login();
		
		adminAction = new AdminAction();
		clientAction = new ClientAction();
		
		Runnable c = new CloseDeposite();
		
		Thread t = new Thread(c);
		
	    t.start();
		
	}
	

public static MBank getInstance(){
		
		if(MBank == null ){
			
			 MBank = new MBank() {
			};
			
		}else{ 
			
			return MBank; 
		}		
		
		return MBank;
		
	}
	
   public Action Login (String name , String password ) throws CheckedExceptions, UncheckedRuntimeException{
	   
	  return mainClasses.Login.Login1(name, password);
	   
   }


@Override
public void addClientAdmin(Client clientBean, Accounts accountBean)
		throws CheckedExceptions, UncheckedRuntimeException {
	
	adminAction.addClientAdmin(clientBean, accountBean);
	
}


@Override
public void UpdateClientAdmin(long clientId, ClientType clientType,
		String comment) throws CheckedExceptions, UncheckedRuntimeException {
	
	adminAction.UpdateClientAdmin(clientId, clientType, comment);
	
}


@Override
public void RemoveClient(long clientId) throws CheckedExceptions,
		UncheckedRuntimeException {
	
	adminAction.RemoveClient(clientId);
	
}


@Override
public ArrayList<Client> ViewAllClientDetails() throws CheckedExceptions,
		UncheckedRuntimeException {
	
	return adminAction.ViewAllClientDetails();
	
}


@Override
public ArrayList<Accounts> ViewAllAccountDetails() throws CheckedExceptions,
		UncheckedRuntimeException {
	
	return adminAction.ViewAllAccountDetails();
	
}


@Override
public ArrayList<Deposits> ViewAllDepositsDetails() throws CheckedExceptions,
		UncheckedRuntimeException {
	
	return adminAction.ViewAllDepositsDetails();
	
}


@Override
public ArrayList<Activity> ViewAllActivitiesDetails() throws CheckedExceptions,
		UncheckedRuntimeException {
	
	return adminAction.ViewAllActivitiesDetails();
	
}


@Override
public void UpdateSystemProperty(String propKey, String propValue)
		throws CheckedExceptions, UncheckedRuntimeException {
	
	adminAction.UpdateSystemProperty(propKey, propValue);
	
}


@Override
public void RemoveAccount(long accountId) throws CheckedExceptions,
		UncheckedRuntimeException {
	
	adminAction.RemoveAccount(accountId);
	
}


@Override
public void updateClient(long clientId, String newAddress, String newEmail,
		String newPhone) throws CheckedExceptions, UncheckedRuntimeException {
	
	clientAction.updateClient(clientId, newAddress, newEmail, newPhone);
	
}


@Override
public void CrateNewDeposit(Deposits depositsBean, long clientId)
		throws CheckedExceptions, UncheckedRuntimeException {
	
	clientAction.CrateNewDeposit(depositsBean, clientId);
	
}


@Override
public void DepositToAccount(long clientId, double amount)
		throws CheckedExceptions, UncheckedRuntimeException {
	
	clientAction.DepositToAccount(clientId, amount);
	
}


@Override
public void WithdrawFromAccount(long clientId, double amount)
		throws CheckedExceptions, UncheckedRuntimeException {
	
	clientAction.WithdrawFromAccount(clientId, amount);
	
}


@Override
public void PreOpenDeposite(long clientId, long depositId)
		throws CheckedExceptions, UncheckedRuntimeException {
	
	clientAction.PreOpenDeposite(clientId, depositId);
	
}


@Override
public Client ViewClientDetails(long clientId) throws CheckedExceptions,
		UncheckedRuntimeException {
	
	return adminAction.ViewClientDetails(clientId);
	
}


@Override
public Accounts ViewAccountDetails(long accountId) throws CheckedExceptions,
		UncheckedRuntimeException {
	
	
	return adminAction.ViewAccountDetails(accountId);
	
}


@Override
public Deposits ViewDepositsDetails(long depositeId) throws CheckedExceptions,
		UncheckedRuntimeException {
	
	return adminAction.ViewDepositsDetails(depositeId);
	
}


@Override
public Activity ViewActivitiesDetails(long id) throws CheckedExceptions,
		UncheckedRuntimeException {
	
	return adminAction.ViewActivitiesDetails(id);
	
}


@Override
public Map<String, String> ViewSystemProperty() throws CheckedExceptions,
		UncheckedRuntimeException {
	
    return clientAction.ViewSystemProperty();
	
	
}

@Override
public Map<String, String> ViewSystemPropertyAdmin() throws CheckedExceptions,
		UncheckedRuntimeException {
	return adminAction.ViewSystemPropertyAdmin();
	
	}


@Override
public Client ViewClientDetailsClient(long clientId) throws CheckedExceptions,
		UncheckedRuntimeException {
	
	return clientAction.ViewClientDetailsClient(clientId);
}


@Override
public Accounts ViewAccountDetailsClient(long accountId)
		throws CheckedExceptions, UncheckedRuntimeException {
	
	return clientAction.ViewAccountDetailsClient(accountId);
}


@Override
public Deposits ViewDepositsDetailsClient(long depositeId)
		throws CheckedExceptions, UncheckedRuntimeException {
	
	return clientAction.ViewDepositsDetailsClient(depositeId);
}


@Override
public Activity ViewActivitiesDetailsClient(long id) throws CheckedExceptions,
		UncheckedRuntimeException {
	
	return clientAction.ViewActivitiesDetailsClient(id);
}
   
}
