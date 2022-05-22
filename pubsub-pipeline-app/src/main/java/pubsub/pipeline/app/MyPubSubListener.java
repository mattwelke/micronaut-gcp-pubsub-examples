package pubsub.pipeline.app;

import java.util.concurrent.atomic.AtomicInteger;

import com.google.pubsub.v1.PubsubMessage;

import io.micronaut.gcp.pubsub.annotation.PubSubListener;
import io.micronaut.gcp.pubsub.annotation.Subscription;
import jakarta.annotation.PreDestroy;

@PubSubListener
public class MyPubSubListener {

    private static AtomicInteger inProgressMessages = new AtomicInteger();

    @Subscription("messages-unvalidated-sub")
    public void onMessage(PubsubMessage message) {
        inProgressMessages.incrementAndGet();
        
        // Process the message
        try {
            // Simulates long running operation
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            inProgressMessages.decrementAndGet();
        }        
    }

    /**
     * Stops the subscriber by closing the underlying Pub/Sub client so that no new messages come in and also allowing
     * all in-progress messages to finish being processed.
     */
    @PreDestroy
    public void stop() {
        // TODO
    }
}
