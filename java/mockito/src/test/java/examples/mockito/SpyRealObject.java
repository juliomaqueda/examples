package examples.mockito;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


public class SpyRealObject {

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

	@Spy ArrayList<String> listSpied;

	@Test
	public void testSpyUsingAnnotation() {
		
		MockitoAnnotations.initMocks(this);
		
		listSpied.add("one");

		assertFalse(listSpied.isEmpty());

		doNothing().when(listSpied).clear();
		listSpied.clear();

		assertFalse(listSpied.isEmpty());

		doCallRealMethod().when(listSpied).clear();

		listSpied.clear();

		assertTrue(listSpied.isEmpty());
	}
}
