package pubsub.pipeline.app;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class MyMessageReceiverFactory {
    
    @Singleton
    public MyMessageReceiver receiver() {
        return new MyMessageReceiver();
    }
}
