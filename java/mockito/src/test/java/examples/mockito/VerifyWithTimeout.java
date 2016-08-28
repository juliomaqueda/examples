package examples.mockito;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import org.junit.Test;


public class VerifyWithTimeout {

	@Test
	public void testVerifyWithTimeout() {

		ActionLauncher actionLauncher = spy(new ActionLauncher());

		actionLauncher.launchAction();

		verify(actionLauncher, timeout(3000)).logResponse();
		
		actionLauncher.launchAction();

		verify(actionLauncher, timeout(3000).times(2)).logResponse();
	}

	private class ActionLauncher {

		public void launchAction() {
			
			Runnable process = new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
						logResponse();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};

			new Thread(process).start();
		}
		
		public void logResponse() {

		}
	}
}
