package examples.mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.mockito.InOrder;


public class VerifyOrder {

	@Test
	public void testVerifyOrder() {

		// A. Single mock whose methods must be invoked in a particular order
		List<String> singleMock = mock(List.class);

		//using a single mock
		singleMock.add("was added first");
		singleMock.add("was added second");

		//create an inOrder verifier for a single mock
		InOrder inOrder = inOrder(singleMock);

		//following will make sure that add is first called with "was added first, then with "was added second"
		inOrder.verify(singleMock).add("was added first");
		inOrder.verify(singleMock).add("was added second");

		// B. Multiple mocks that must be used in a particular order
		List<String> firstMock = mock(List.class);
		List<String> secondMock = mock(List.class);

		//using mocks
		firstMock.add("was called first");
		secondMock.add("was called second");

		//create inOrder object passing any mocks that need to be verified in order
		InOrder newInOrder = inOrder(firstMock, secondMock);

		//following will make sure that firstMock was called before secondMock
		newInOrder.verify(firstMock).add("was called first");
		newInOrder.verify(secondMock).add("was called second");

		// Oh, and A + B can be mixed together at will
	}
}

/*
 * Verification in order is flexible - you don't have to verify all interactions one-by-one but only those that you are
 * interested in testing in order. Also, you can create an InOrder object passing only the mocks that are relevant
 * for in-order verification. 
 */
