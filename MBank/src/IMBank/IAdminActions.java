package IMBank;

import java.util.ArrayList;
import java.util.Map;

import mainClasses.Accounts;
import mainClasses.Activity;
import mainClasses.Client;
import mainClasses.ClientType;
import mainClasses.Deposits;
import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;

public interface IAdminActions {

	// add new client and account and activity like a first deposit 
	//get of the commission from the balance also calculate the balance off
	//the account after commission. States credit limit and client type
	public abstract void addClientAdmin(Client clientBean, Accounts accountBean)
			throws CheckedExceptions, UncheckedRuntimeException;

	// Admin can to update client bean when you update the client type 
	// this is atomaticly updating a Account credit limit in actualy client 
	public abstract void UpdateClientAdmin(long clientId,
			ClientType clientType, String comment) throws CheckedExceptions,
			UncheckedRuntimeException;

	//remove all client properties (all Account , all deposits and all Activities)
	public abstract void RemoveClient(long clientId) throws CheckedExceptions,
			UncheckedRuntimeException;

	//you can chose a Client by his id add a system will show you all his details (Accounts , Activity and Deposits) 
	public abstract Client ViewClientDetails(long clientId)
			throws CheckedExceptions, UncheckedRuntimeException;

	//you can get only one Activity detail by his id
	public abstract Activity ViewActivitiesDetails(long id)
			throws CheckedExceptions, UncheckedRuntimeException;

	//you can see Account details by account id
	public abstract Accounts ViewAccountDetails(long accountId)
			throws CheckedExceptions, UncheckedRuntimeException;

	//you can see Deposit details by deposit id 
	public abstract Deposits ViewDepositsDetails(long depositeId)
			throws CheckedExceptions, UncheckedRuntimeException;

	//you can see all client in the bank system 
	public abstract ArrayList<Client> ViewAllClientDetails() throws CheckedExceptions,
			UncheckedRuntimeException;

	//you can see all Account is bank system
	public abstract ArrayList<Accounts> ViewAllAccountDetails() throws CheckedExceptions,
			UncheckedRuntimeException;

	//you can see all deposits in a bank system
	public abstract ArrayList<Deposits> ViewAllDepositsDetails() throws CheckedExceptions,
			UncheckedRuntimeException;

	//you can see all activities in the bank system
	public abstract ArrayList<Activity> ViewAllActivitiesDetails() throws CheckedExceptions,
			UncheckedRuntimeException;

	//you can see all properties in the bank system
	public abstract Map<String, String> ViewSystemPropertyAdmin() throws CheckedExceptions,
			UncheckedRuntimeException;

	//you can update all system properties by propKey
	public abstract void UpdateSystemProperty(String propKey, String propValue)
			throws CheckedExceptions, UncheckedRuntimeException;

	//you can remove Account by accountId and also that give a deskripshion in Activity table
	public abstract void RemoveAccount(long accountId)
			throws CheckedExceptions, UncheckedRuntimeException;

}