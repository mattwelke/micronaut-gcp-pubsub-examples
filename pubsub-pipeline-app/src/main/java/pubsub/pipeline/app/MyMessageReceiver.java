package pubsub.pipeline.app;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMessageReceiver implements MessageReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(MyMessageReceiver.class);

    @Override
    public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
        // Handle incoming message, then ack the received message.
        LOG.info("Id: " + message.getMessageId());
        LOG.info("Data: " + message.getData().toStringUtf8());
        consumer.ack();
    }
}
