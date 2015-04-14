package TestClasses;

import mainClasses.AdminAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class RemoveAccount {

	
	@Test
	public void testRemoveAccountt() throws CheckedExceptions, UncheckedRuntimeException {
		
		IAdminActions admin = new AdminAction();
		
		admin.RemoveAccount((long)5);
		
	}
}
