package TestClasses;

import mainClasses.CloseDeposite;

import org.junit.Test;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;

public class CloseDepositsTest  {

	
	@Test
	public void testCloseDeposits() throws CheckedExceptions, UncheckedRuntimeException{
		
		Runnable c = new CloseDeposite();
		
		Thread t = new Thread(c);
		
	    t.start();
	}
	
}
