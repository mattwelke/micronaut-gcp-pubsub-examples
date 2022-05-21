# micronaut-gcp-pubsub-examples

Examples of apps created to work with GCP Pub/Sub:

## pubsub-write-app

An app created using `mn create-messaging-app` with dependencies from an HTTP server added to it, so that HTTP requests can be sent to its controller, resulting in a Pub/Sub message being published to a topic with the HTTP request body's contents.

## pubsub-read-app

An app created using `mn create-messaging-app` without any dependencies added that listens to a subscription associated with the above mentioned topic, logging the message contents. Does not have controllers because it doesn't need them.
