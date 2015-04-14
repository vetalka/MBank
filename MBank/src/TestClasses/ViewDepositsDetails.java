package TestClasses;

import mainClasses.AdminAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class ViewDepositsDetails {
	
	@Test
	public void testViewAllDepositsDetails() throws CheckedExceptions, UncheckedRuntimeException {
		
		IAdminActions admin = new AdminAction();
		
		admin.ViewDepositsDetails((long)5);
		
	}
}
