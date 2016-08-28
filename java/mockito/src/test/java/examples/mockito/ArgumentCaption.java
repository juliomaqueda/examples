package examples.mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;


public class ArgumentCaption {

	@Test
	public void testArgumentCaptor() {

		NodeManager nodeManager = spy(new NodeManager());

		nodeManager.validateNode(new Node());

		ArgumentCaptor<Node> argument = ArgumentCaptor.forClass(Node.class);

		verify(nodeManager).validateNode(argument.capture());

		assertTrue(argument.getValue().valid);
	}

	@Captor ArgumentCaptor<Node> captor;

	@Test
	public void testArgumentCaptorUsingAnnotation() {

		MockitoAnnotations.initMocks(this);

		NodeManager nodeManager = spy(new NodeManager());

		nodeManager.validateNode(new Node());

		verify(nodeManager).validateNode(captor.capture());

		assertTrue(captor.getValue().valid);
	}

	private class NodeManager {
		
		public void validateNode(Node node) {
			node.valid = true;
		}
	}

	private class Node {
		
		public boolean valid;
	}
}
