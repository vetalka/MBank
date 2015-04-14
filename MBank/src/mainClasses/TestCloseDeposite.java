package mainClasses;

public class TestCloseDeposite {

	public static void main(String[] args) {
		
		

		Runnable c = new CloseDeposite();
		
		Thread t = new Thread(c);
		
	    t.start();
		
	}

}
