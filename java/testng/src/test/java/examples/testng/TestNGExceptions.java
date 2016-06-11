package examples.testng;

import org.testng.annotations.Test;


public class TestNGExceptions {

	@Test(expectedExceptions  = ArithmeticException.class)  
	public void arithmeticException() {

		System.out.println(1 / 0);
	}


	@Test(expectedExceptions  = IndexOutOfBoundsException.class)  
	public void indexOutOfBounds() {

		System.out.println(new int[]{0}[1]);
	}
}
