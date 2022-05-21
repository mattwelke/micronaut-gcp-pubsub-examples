package pubsub.pipeline.app;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Message(String contents) {
    private static final Logger LOG = LoggerFactory.getLogger(Message.class);

    /**
     * Validates the Message.
     * @return A string containing the error message if the Message was invalid. Otherwise, nothing.
     */
    Optional<String> validate() {
        try {
            Thread.sleep(2000);
            LOG.info("Processing 1/3 complete.");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (this.contents.length() == 0) {
            return Optional.of("\"contents\" length must be at least 1 character long.");
        }

        try {
            Thread.sleep(2000);
            LOG.info("Processing 2/3 complete.");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (this.contents.length() > 100) {
            return Optional.of("\"contents\" length must be less than or equal to 100 characters long.");
        }

        try {
            Thread.sleep(2000);
            LOG.info("Processing 3/3 complete.");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
