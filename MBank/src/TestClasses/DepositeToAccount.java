package TestClasses;

import mainClasses.ClientAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IClientActions;

public class DepositeToAccount {
	
	@Test(expected=UncheckedRuntimeException.class)

	public void testCrateNewDeposit() throws CheckedExceptions, UncheckedRuntimeException{
		
		
		IClientActions client = new ClientAction();
		
		client.DepositToAccount((long)1, 22);
		
	}

}
