package pubsub.pipeline.app;

import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import jakarta.inject.Singleton;

@Singleton
public class Coordinator implements ApplicationEventListener<StartupEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(Coordinator.class);

    private MessageReceiver receiver;

    Coordinator(MyMessageReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void onApplicationEvent(StartupEvent event) {
        // Create Pub/Sub stuff, so that Micronaut does not control its lifecycle,
        // instead having the bean control the
        // Pub/Sub lifecycle.
        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of("micronaut-gcp-pubsub-examples",
                "messages-unvalidated-sub");

        Subscriber subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();

        subscriber.startAsync().awaitRunning();

        LOG.info("Listening for messages on " +  subscriptionName.toString() + ".");
    }
}
