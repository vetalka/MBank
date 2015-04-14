package Interface;

import java.util.ArrayList;
import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import mainClasses.Deposits;

public interface IDepositsJDBCDAO {

	public void CrateNewDeposit(Deposits depositsBean)  throws CheckedExceptions, UncheckedRuntimeException;

	public void ViewDepositsDetails(long clientId)  throws CheckedExceptions, UncheckedRuntimeException;

	public  ArrayList <Deposits> ViewAllDepositsDetails()  throws CheckedExceptions, UncheckedRuntimeException;
	
	public Deposits getDepositeBean (long depositeId )  throws CheckedExceptions, UncheckedRuntimeException;

	public Map<Long, String> getDepositeType()  throws CheckedExceptions, UncheckedRuntimeException;
	
	public Deposits getDepositeBeanByDepositeId (long depositId , long clientId )  throws CheckedExceptions, UncheckedRuntimeException;

	public void preOpenDeposite(long depositId)  throws CheckedExceptions, UncheckedRuntimeException;
	
	public ArrayList<Deposits> ViewDepositsByClientId(long clientId)  throws CheckedExceptions, UncheckedRuntimeException;


	
}
