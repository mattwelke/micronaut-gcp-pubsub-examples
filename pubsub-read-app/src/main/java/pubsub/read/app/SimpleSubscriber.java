package pubsub.read.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.gcp.pubsub.annotation.PubSubListener;
import io.micronaut.gcp.pubsub.annotation.Subscription;

@PubSubListener
public class SimpleSubscriber {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleSubscriber.class);

    /**
     * Processes Animal messages. Uses Micronaut defaults for listeners which assume message is JSON, with Micronaut
     * parsing the JSON message into the class.
     * @param person The parsed Animal message.
     */
    @Subscription("people-sub")
    public void onMessage(Person person) {
        LOG.info("Received Person message: " + person);
    }
}
