package TestClasses;

import mainClasses.ClientAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IClientActions;

public class WithdrawFromAccount {

	@Test(expected=UncheckedRuntimeException.class)

	public void testCrateNewDeposit() throws CheckedExceptions, UncheckedRuntimeException {
		
		
		IClientActions client = new ClientAction();
		
		client.WithdrawFromAccount((long)1, 12222);
		
	}
}
