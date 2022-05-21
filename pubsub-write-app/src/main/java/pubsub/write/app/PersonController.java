package pubsub.write.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/people")
public class PersonController {
    
    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    private Publisher publisher;

    public PersonController(Publisher publisher) {
        this.publisher = publisher;
    }

    @Post()
    public void create(Person person) {
        LOG.info("Processing request with Person: " + person + ". Publishing Person to Pub/Sub.");
        publisher.send(person);
        LOG.info("Published Person to Pub/Sub. Finished processeing request.");
    }
}
