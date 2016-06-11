package examples.testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestNGEnableDisable {

	Calculator calculator;


	@BeforeClass
	public void setup() {

		System.out.println("setup()");
		calculator = new Calculator();
	}


	@AfterClass
	public void tearDown() {

		System.out.println("tearDown()");
		calculator = null;
	}


	@BeforeMethod
	public void beforeMethod() {

		System.out.println("beforeMethod()");
	}


	@AfterMethod
	public void afterMethod() {

		System.out.println("afterMethod()");
	}


	@Test
	public void testAdd() {

		System.out.println("testAdd()");
		Assert.assertEquals(calculator.add(3, 4), 7);
	}


	@Test(enabled = false)
	public void testSubtract() {

		System.out.println("testSubtract()");
		Assert.assertEquals(calculator.subtract(5, 2), 3);
	}
}
