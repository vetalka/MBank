package IMBank;

import java.util.Map;

import mainClasses.Accounts;
import mainClasses.Activity;
import mainClasses.Client;
import mainClasses.Deposits;
import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;

public interface IClientActions {

	// update to client a newAddress newEmail and newPhone by his client id
	public abstract void updateClient(long clientId, String newAddress,
			String newEmail, String newPhone) throws CheckedExceptions,
			UncheckedRuntimeException;

	//Created a new deposit with calculate the type long or short and get off 
	//a commission by the client type and insert this to activity off this client
	public abstract void CrateNewDeposit(Deposits depositsBean, long clientId)
			throws CheckedExceptions, UncheckedRuntimeException;

	//doing deposit and update credit limit and client type 
	public abstract void DepositToAccount(long clientId, double amount)
			throws CheckedExceptions, UncheckedRuntimeException;

	//withdraw from account and update credit limit and client type
	//if a balance of this account is minus its throws exsepshion 
	public abstract void WithdrawFromAccount(long clientId, double amount)
			throws CheckedExceptions, UncheckedRuntimeException;

	//pre opened the deposit and set credit limit and client type
	public abstract void PreOpenDeposite(long clientId, long depositId)
			throws CheckedExceptions, UncheckedRuntimeException;

	//view all details of this client (Account , Activity , Deposit)
	public abstract Client ViewClientDetailsClient(long clientId)
			throws CheckedExceptions, UncheckedRuntimeException;

	//view Account details by accountId
	public abstract Accounts ViewAccountDetailsClient(long accountId)
			throws CheckedExceptions, UncheckedRuntimeException;

	//view Deposit details by deposit Id
	public abstract Deposits ViewDepositsDetailsClient(long depositeId)
			throws CheckedExceptions, UncheckedRuntimeException;

	//View Activity details by activity Id
	public abstract Activity ViewActivitiesDetailsClient(long id)
			throws CheckedExceptions, UncheckedRuntimeException;

	//view all system properties
	public abstract Map<String, String> ViewSystemProperty() throws CheckedExceptions,
			UncheckedRuntimeException;

}