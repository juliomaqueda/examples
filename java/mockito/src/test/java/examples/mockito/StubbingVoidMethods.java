package examples.mockito;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class StubbingVoidMethods {

	@Test
	public void testStubbingVoidMethods() {

		//You can mock concrete classes, not just interfaces
		List<String> mockedList = mock(List.class);

		mockedList.add("one");

		assertFalse(mockedList.isEmpty());

		doNothing().when(mockedList).clear();

		mockedList.clear();

		assertFalse(mockedList.isEmpty());

		doReturn(true).when(mockedList).isEmpty();

		assertTrue(mockedList.isEmpty());
	}

	@Test
	public void testVoidMethodSpy() {

		List<String> list = new ArrayList<>();

		list.add("one");

		List<String> spy = spy(list);

		doNothing().when(spy).clear();
		spy.clear();

		assertFalse(list.isEmpty());

		doCallRealMethod().when(spy).clear();

		spy.clear();

		assertTrue(spy.isEmpty());
	}
}
