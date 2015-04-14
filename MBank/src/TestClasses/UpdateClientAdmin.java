package TestClasses;

import mainClasses.AdminAction;
import mainClasses.ClientType;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class UpdateClientAdmin {

	@Test(expected=UncheckedRuntimeException.class)

	public void testUpdateClient() throws CheckedExceptions, UncheckedRuntimeException{
		
        IAdminActions admin = new AdminAction();
	
        admin.UpdateClientAdmin((long) 1, ClientType.GOLD, "");

}
}