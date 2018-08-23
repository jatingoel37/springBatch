package learn.retry.retrycallbacks;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;

public class AttemptsConfigurableRetryCallback implements RetryCallback<String, Throwable> {

	private int attempts;

	public AttemptsConfigurableRetryCallback(int attempts) {
		this.attempts = attempts;
	}

	public String doWithRetry(RetryContext retryContext) throws Throwable {
		if (retryContext.getRetryCount() < attempts) {
			throw new IllegalStateException(retryContext.getRetryCount() + " < " + attempts);
		}
		return "SUCCESS";
	}

}
