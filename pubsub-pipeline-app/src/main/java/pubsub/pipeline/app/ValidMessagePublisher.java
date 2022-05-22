package pubsub.pipeline.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PreDestroy;
import jakarta.inject.Singleton;

@Singleton
public class ValidMessagePublisher {

    private static final Logger LOG = LoggerFactory.getLogger(ValidMessagePublisher.class);
    
    private ValidMessagePublisherClient client;

    // Keep a reference to the subscriber into which this bean is injected so help with shutdown order.
    private MyPubSubListener subscriber;

    public ValidMessagePublisher(ValidMessagePublisherClient client) {
        this.client = client;
    }

    /**
     * Set a reference to a subscriber. This is meant to be the subscriber bean That Micronaut injected this publisher bean into.
     * 
     * @param subscriber The reference to the publisher's subscriber.
     */
    public void setSubscriber(MyPubSubListener subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * Wrapper for publishing the message. Defers to the client auto-generated client from micronaut-gcp for
     * publishing.
     * @param message The message to be published.
     */
    public void publishMessage(Message message) {
        this.client.send(message);
    }

    @PreDestroy
    public void close() throws Exception {
        LOG.info("PreDestroy hook for ValidMessagePublisher started.");

        // TODO: Stop the subscriber.

        LOG.info("PreDestroy hook for ValidMessagePublisher finished.");
    }
}
