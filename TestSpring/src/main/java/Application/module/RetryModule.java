package Application.module;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RetryModule {
    private int retryCount;

    @Retryable(value = {IOException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000L))
    public void doStuff(int devider, int deviden) throws IOException {
        System.out.println("doStuff is running");
        if (devider % deviden != 0) {
            throw new IOException();
        }

    }
    @Recover
    public void doRecover(){
        System.out.println("Recover is running");
    }

}
