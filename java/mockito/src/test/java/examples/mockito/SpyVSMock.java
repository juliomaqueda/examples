package examples.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class SpyVSMock {

	@Mock
	private List<String> mockList;

	@Spy
	private List<String> spyList = new ArrayList<String>();

	@Test
	public void testMockList() {
		//by default, calling the methods of mock object will do nothing
		mockList.add("test");
		assertNull(mockList.get(0));
	}

	@Test
	public void testSpyList() {
		//spy object will call the real method when not stub
		spyList.add("test");
		assertEquals("test", spyList.get(0));
	}

	@Test
	public void testMockWithStub() {
		//try stubbing a method
		String expected = "Mock 100";

		when(mockList.get(100)).thenReturn(expected);

		assertEquals(expected, mockList.get(100));
	}

	@Test
	public void testSpyWithStub() {
		//stubbing a spy method will result the same as the mock object
		String expected = "Spy 100";

		//take note of using doReturn instead of when
		doReturn(expected).when(spyList).get(100);

		assertEquals(expected, spyList.get(100));
	}
}

/*
 * Both can be used to mock methods or fields. The difference is that in mock, you are creating a complete mock
 * or fake object while in spy, there is the real object and you just spying or stubbing specific methods of it.
 * 
 * When using mock objects, the default behavior of the method when not stub is do nothing. Simple means, if its
 * a void method, then it will do nothing when you call the method or if its a method with a return then it may
 * return null, empty or the default value.
 * 
 * While in spy objects, of course, since it is a real method, when you are not stubbing the method, then it will
 * call the real method behavior. If you want to change and mock the method, then you need to stub it.
 */
