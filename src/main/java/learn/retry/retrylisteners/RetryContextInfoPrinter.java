package learn.retry.retrylisteners;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

public class RetryContextInfoPrinter implements RetryListener {

	public <T, E extends Throwable> boolean open(RetryContext var1, RetryCallback<T, E> var2) {
		return true;
	}

	public <T, E extends Throwable> void close(RetryContext var1, RetryCallback<T, E> var2, Throwable var3) {
		System.out.println("Closing RetryContextInfoPrinter");
		System.out.println("[RetryContextInfoPrinter.close]Retry count = " + var1.getRetryCount());
		System.out.println("[RetryContextInfoPrinter.close]Is exhausted = " + var1.isExhaustedOnly());
	}

	public <T, E extends Throwable> void onError(RetryContext var1, RetryCallback<T, E> var2, Throwable var3) {
		System.out.println("onError RetryContextInfoPrinter");
		System.out.println("[RetryContextInfoPrinter.onError]Retry count = " + var1.getRetryCount());
		System.out.println("[RetryContextInfoPrinter.onError]Is exhausted = " + var1.isExhaustedOnly());
	}

}
