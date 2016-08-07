package examples.mockito;

import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.Test;


public class Stubbing {

	@Test
	public void testStubbing() {

		//You can mock concrete classes, not just interfaces
		LinkedList<String> mockedList = mock(LinkedList.class);

		//stubbing
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new RuntimeException());

		//following prints "first"
		System.out.println(mockedList.get(0));

		//following throws runtime exception
		try {
			System.out.println(mockedList.get(1));
		}
		catch (RuntimeException e) {
			System.out.println("Exception catched");
		}

		//following prints "null" because get(999) was not stubbed
		System.out.println(mockedList.get(999));

		//Although it is possible to verify a stubbed invocation, usually it's just redundant
		//If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
		//If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
		verify(mockedList).get(0);
		verify(mockedList).get(1);
		verify(mockedList).get(999);
	}

	@Test(expected = RuntimeException.class)
	public void testThrowedException() {

		//You can mock concrete classes, not just interfaces
		LinkedList<String> mockedList = mock(LinkedList.class);

		doThrow(new RuntimeException()).when(mockedList).clear();

		//following throws RuntimeException:
		mockedList.clear();
	}
}

/*
 * By default, for all methods that return a value, a mock will return either null, a a primitive/primitive wrapper value,
 * or an empty collection, as appropriate. For example 0 for an int/Integer and false for a boolean/Boolean.
 * 
 * Stubbing can be overridden: for example common stubbing can go to fixture setup but the test methods can override it.
 * Please note that overridding stubbing is a potential code smell that points out too much stubbing.
 * 
 * Once stubbed, the method will always return a stubbed value, regardless of how many times it is called.
 * 
 * Last stubbing is more important - when you stubbed the same method with the same arguments many times.
 * Other words: the order of stubbing matters but it is only meaningful rarely, e.g. when stubbing exactly the same method
 * calls or sometimes when argument matchers are used, etc.
 */
