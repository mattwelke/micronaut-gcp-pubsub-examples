package pubsub.pipeline.app;

import io.micronaut.gcp.pubsub.annotation.PubSubClient;
import io.micronaut.gcp.pubsub.annotation.Topic;

@PubSubClient
public interface ValidMessagePublisherClient {

    /**
     * Publishes a validated message.
     * @param message The validated message.
     */
    @Topic("messages-valid")
    void send(Message message);
}
