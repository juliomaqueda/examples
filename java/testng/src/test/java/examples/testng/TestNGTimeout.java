package examples.testng;

import org.testng.annotations.Test;


public class TestNGTimeout {

	@Test(timeOut = 1000)  
	public void infinity() {

		while (true) break;
	}  
}
