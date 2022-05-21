package pubsub.write.app;

import io.micronaut.gcp.pubsub.annotation.PubSubClient;
import io.micronaut.gcp.pubsub.annotation.Topic;

@PubSubClient
public interface Publisher {
    
    @Topic("people")
    void send(Person person);
}
