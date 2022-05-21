package pubsub.pipeline.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.gcp.pubsub.annotation.PubSubListener;
import io.micronaut.gcp.pubsub.annotation.Subscription;

@PubSubListener
public class UnvalidatedMessageSubscriber {

    private static final Logger LOG = LoggerFactory.getLogger(UnvalidatedMessageSubscriber.class);

    private ValidMessagePublisher publisher;

    private static int count = 0;

    public UnvalidatedMessageSubscriber(ValidMessagePublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * Processes unvalidated messages. Uses Micronaut defaults for listeners which assume message is JSON, with
     * Micronaut parsing the JSON message into the class.
     * @param message The parsed unvalidated message.
     */
    @Subscription("taxi-sub")
    public void onMessage(Message message) {
        count++;
        
        LOG.info("Received message # since startup: " + count);
        
        LOG.info("Received message: " + message + ". Will validate it.");

        var error = message.validate();

        if (error.isPresent()) {
            LOG.info("Message was invalid. Reason: " + error.get() + " Will not publish.");
            return;
        }
        LOG.info("Message was valid. Will publish.");

        publisher.send(message);
        LOG.info("Published valid message.");
    }
}
