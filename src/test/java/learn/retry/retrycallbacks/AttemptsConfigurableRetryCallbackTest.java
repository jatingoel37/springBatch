package learn.retry.retrycallbacks;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.retry.TerminatedRetryException;
import org.springframework.retry.support.RetryTemplate;

import learn.retry.exceptions.RecoveryException;
import learn.retry.retrylisteners.RetryContextInfoPrinter;
import learn.retry.retrylisteners.VetoExercisingListener;

public class AttemptsConfigurableRetryCallbackTest {

	/**
	 * Veto wont let execute the callback
	 * 
	 * @throws Throwable
	 */
	@Test(expected = TerminatedRetryException.class)
	public void testVeto() throws Throwable {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.registerListener(new VetoExercisingListener());
		retryTemplate.execute(new AttemptsConfigurableRetryCallback(4));
	}

	/**
	 * No recovery callback and all retry attempts fails an hence the business
	 * exception
	 * 
	 * @throws Throwable
	 */
	@Test(expected = IllegalStateException.class)
	public void testFailRetry() throws Throwable {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.registerListener(new RetryContextInfoPrinter());
		retryTemplate.execute(new AttemptsConfigurableRetryCallback(4));
	}

	/**
	 * Recovery callback response
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testFailRetryButWithRecovery() throws Throwable {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.registerListener(new RetryContextInfoPrinter());
		String response = retryTemplate.execute(new AttemptsConfigurableRetryCallback(4), retryContext -> "FALLBACK");
		Assert.assertEquals("FALLBACK", response);
	}

	/**
	 * Recovery callback response but with Exception
	 * 
	 * @throws Throwable
	 */
	@Test(expected = RecoveryException.class)
	public void testFailRetryButWithRecoveryAndException() throws Throwable {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.registerListener(new RetryContextInfoPrinter());
		retryTemplate.execute(new AttemptsConfigurableRetryCallback(4), retryContext -> {
			throw new RecoveryException("fallback exception");
		});
	}

}
