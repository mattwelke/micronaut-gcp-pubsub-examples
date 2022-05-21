package pubsub.pipeline.app;

import com.google.api.gax.core.InstantiatingExecutorProvider;
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

    public Coordinator(MyMessageReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void onApplicationEvent(StartupEvent event) {
        createSubscriber().startAsync().awaitRunning();
        LOG.info("Listening for messages on subscription.");
    }

    /**
     * Create Pub/Sub stuff, so that Micronaut does not control its lifecycle, instead having this bean's code control
     * the Pub/Sub lifecycle, so we can shut down the subscriber before shutting down the publisher.
     */
    private Subscriber createSubscriber() {
        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of("micronaut-gcp-pubsub-examples",
                "messages-unvalidated-sub");

        // Setting the number of threads for processing messages. This could come from config too.
        InstantiatingExecutorProvider executorProvider = InstantiatingExecutorProvider.newBuilder()
                .setExecutorThreadCount(50).build();

        return Subscriber
                .newBuilder(subscriptionName, receiver)
                .setExecutorProvider(executorProvider)
                .build();
    }

    public void shutdown() {

    }
}
