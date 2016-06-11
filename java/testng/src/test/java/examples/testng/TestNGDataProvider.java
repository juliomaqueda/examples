package examples.testng;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestNGDataProvider {

	@DataProvider(name = "addMethodDataProvider")
	public Object[][] dataProvider() {

		return new Object[][] { { 2, 5, 7 }, { 3, 7, 10 }, { 4, 5, 9 } };
	}


	@Test(dataProvider = "addMethodDataProvider")
	public void testAddMethod(int a, int b, int result) {

		Calculator calculator = new Calculator();
		Assert.assertEquals(calculator.add(a, b), result);
	}


	@Test(dataProvider = "subtractMethodDataProvider", dataProviderClass = CalculatorDataProvider.class)
	public void testSubtractMethod(int a, int b, int expectedResult) {

		Calculator calculator = new Calculator();
		Assert.assertEquals(calculator.subtract(a, b), expectedResult);
	}


	@Test(dataProvider = "multiplyMethodDataProvider", dataProviderClass = CalculatorDataProvider.class)
	public void testMultiplyMethod(int a, int b, int expectedResult) {

		Calculator calculator = new Calculator();
		Assert.assertEquals(calculator.multiply(a, b), expectedResult);
	}


	public static class CalculatorDataProvider {

		@DataProvider(name = "subtractMethodDataProvider")
		public static Object[][] subtractMethodDataProvider() {

			return new Object[][] { { 2, 5, -3 }, { 3, 7, -4 }, { 24, 5, 19 } };
		}


		@DataProvider(name = "multiplyMethodDataProvider")
		public static Object[][] multiplyMethodDataProvider() {

			return new Object[][] { { 2, 5, 10 }, { 3, 7, 21 }, { 4, 5, 20 } };
		}
	}
}