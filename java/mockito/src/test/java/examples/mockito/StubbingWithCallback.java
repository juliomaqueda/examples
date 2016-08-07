package examples.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class StubbingWithCallback {

	@Test
	public void testStubbingVoidMethods() {

		List<String> mockedList = mock(List.class);

		when(mockedList.get(anyInt())).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				Object mock = invocation.getMock();
				return "called with arguments: " + args[0];
			}
		});

		assertEquals(mockedList.get(0), "called with arguments: 0");
		assertEquals(mockedList.get(1), "called with arguments: 1");
		assertEquals(mockedList.get(999), "called with arguments: 999");
	}
}
