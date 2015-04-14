package TestClasses;

import mainClasses.CloseDeposite;

public class TestMBank {
	
   public static void main(String[] args) {
		
		

		Runnable c = new CloseDeposite();
		
		Thread t = new Thread(c);
		
	    t.start();
		
	}

}
