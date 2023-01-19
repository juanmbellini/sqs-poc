# SQS producer-consumer POC

A Proof of Concept using SQS as queue for async communication between two components, using Spring Cloud AWS.


## Getting started

### Prerequisites

You need `Docker` and `Java 11`. Additionaly, `awscli-local` is recommended (`pip install awscli-local[ver1]`).


### Environment configuration

In order to run everything locally, a `docker-compose` for having `localstack` running is included.
Just run:

```shell
$ docker-compose -f docker/localstack.yml
```

Then, you must create the SQS queue. A script for that is included:

```shell
./create_queue.sh
```

The response will be something of the sort:

```json
{
    "QueueUrl": "http://localhost:4566/000000000000/ai_fetcher_playground_sqspoc_queues_dummy-creation-event.fifo"
}
```

Note that the script is idempotent. The URL will be always the same. If nothing is changed, the java projects are already configured with that URL.


## Running the projects

Start two terminals and `cd` into `./producer` in one of them, and into `./consumer` in the other.

Then run in each terminal:

```shell
./gradlew bootRun
```

That will start each component. You will have the producer listening HTTP in port 8080, and the consumer polling the SQS queue.

When sending the request to the producer, it will process it and push it to the queue. Then, the consumer will poll the queue and consume the event, and log it as INFO.
The request must be sent to 

```http
POST http://localhost:8080/hello/world
```

You can try with the following body:

```json
{
    "name": "Juan Marcos Bellini",
    "email": "juanmbellini@gmail.com"
}
```

An easy way to test it would be using the following command:

```shell
 curl http://localhost:8080/hello/world -X POST -H content-type:application/json --data '{"name": "Juan Marcos Bellini","email": "juanmbellini@gmail.com"}'
```
