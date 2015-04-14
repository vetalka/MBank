package TestClasses;

import java.sql.Date;
import java.util.Calendar;

import mainClasses.ClientAction;
import mainClasses.Deposits;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IClientActions;

public class CrateNewDeposit {
	
	

	@Test(expected=UncheckedRuntimeException.class)

	public void testCrateNewDeposit() throws CheckedExceptions, UncheckedRuntimeException{
		
		
		IClientActions client = new ClientAction();
		
		Calendar openingDate = Calendar.getInstance();
		Date start=new Date(openingDate.getTimeInMillis());
		Calendar closingDate = Calendar.getInstance();
		closingDate.roll(Calendar.YEAR, 0);
		Date end=new Date(closingDate.getTimeInMillis());
		
		Deposits d = new Deposits( (long)2, 100,start,end );
		

		client.CrateNewDeposit(d ,(long)2);
		
		
	}

}
