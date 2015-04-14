package TestClasses;

import mainClasses.AdminAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class RemoveClient {

	@Test
	public void testRemoveClient() throws CheckedExceptions, UncheckedRuntimeException {
		
		IAdminActions admin = new AdminAction();
		
		admin.RemoveClient((long) 3);
		
	}
}
