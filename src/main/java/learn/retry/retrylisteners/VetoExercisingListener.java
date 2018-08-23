package learn.retry.retrylisteners;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.util.Assert;

public class VetoExercisingListener implements RetryListener {

	public <T, E extends Throwable> boolean open(RetryContext var1, RetryCallback<T, E> var2) {
		return false;
	}

	public <T, E extends Throwable> void close(RetryContext var1, RetryCallback<T, E> var2, Throwable var3) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("[VetoExercisingListener.close]");
		Assert.isNull(var3, "Should always be null");
		System.out.println("Throwable is null");
		System.out.println("-----------------------------------------------------------");
	}

	public <T, E extends Throwable> void onError(RetryContext var1, RetryCallback<T, E> var2, Throwable var3) {
		System.out.println("-----------------------------------------------------------");
		System.out.println("[VetoExercisingListener.onError]Should never print");
		System.out.println("-----------------------------------------------------------");
	}

}
