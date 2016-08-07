package examples.mockito;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ArgumentMatcher {

	@Test
	public void testArgumentMatcher() {

		//mock creation
		List<String> mockedList = mock(List.class);

		//stubbing using built-in anyInt() and anyString() argument matcher
		when(mockedList.get(anyInt())).thenReturn("element");
		when(mockedList.contains(anyString())).thenReturn(true);

		//following prints "element" and "foo"
		System.out.println(mockedList.get(999));
		System.out.println(mockedList.contains("foo"));

		//you can also verify using an argument matcher
		verify(mockedList).get(999);
		verify(mockedList).get(anyInt());
	}

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testMultipleReturns() {

		//mock creation
		List<String> mockedList = mock(List.class);

		when(mockedList.get(anyInt())).thenReturn("one", "two", "three");

		assertEquals(mockedList.get(0), "one");
		assertEquals(mockedList.get(0), "two");
		assertEquals(mockedList.get(0), "three");

		//Any consecutive call
		assertEquals(mockedList.get(0), "three");
		assertEquals(mockedList.get(0), "three");

		when(mockedList.get(anyInt()))
		.thenReturn("foo")
		.thenThrow(new RuntimeException());

		assertEquals(mockedList.get(0), "foo");

		//Any consecutive call will throw RuntimeException
		exception.expect(RuntimeException.class);

		mockedList.get(0);
	}
}

/*
 * Argument matchers can also be written as Java 8 Lambdas.
 * 
 * stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
 * when(mockedList.contains(argThat(isValid()))).thenReturn("element");
 */
