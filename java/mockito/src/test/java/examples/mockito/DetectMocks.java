package examples.mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;


public class DetectMocks {

	@Test
	public void testDetectMock() {

		//mock creation
		List<String> mockedList = mock(List.class);

		Date spiedDate = spy(new Date());

		assertTrue(Mockito.mockingDetails(mockedList).isMock());
		assertFalse(Mockito.mockingDetails(mockedList).isSpy());

		assertTrue(Mockito.mockingDetails(spiedDate).isMock());
		assertTrue(Mockito.mockingDetails(spiedDate).isSpy());
	}
}
