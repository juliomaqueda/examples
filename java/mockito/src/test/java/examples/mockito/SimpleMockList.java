package examples.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;


public class SimpleMockList {

	@Test
	public void testSimpleMockList() {

		//mock creation
		List<String> mockedList = mock(List.class);

		//using mock object
		mockedList.add("one");
		mockedList.clear();

		//verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}
	
	@Test
	public void testMockInOneLine() {
		
		List<String> mockedList = when(mock(List.class).size()).thenReturn(100).getMock();
		
		assertEquals(100, mockedList.size());
	}
}

/*
 *  Once created, a mock will remember all interactions.
 *  Then you can selectively verify whatever interactions you are interested in. 
 */
