package TestClasses;

import mainClasses.AdminAction;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import IMBank.IAdminActions;

public class UpdateSystemProperty {

	
	@Test
	public void testUpdateSystemProperty() throws CheckedExceptions, UncheckedRuntimeException{
		
        IAdminActions admin = new AdminAction();
	
        admin.UpdateSystemProperty("commission_rate", "0.5");

}
}
