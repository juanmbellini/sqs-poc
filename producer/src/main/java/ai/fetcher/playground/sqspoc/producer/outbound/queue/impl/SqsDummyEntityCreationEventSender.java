package ai.fetcher.playground.sqspoc.producer.outbound.queue.impl;

import ai.fetcher.playground.sqspoc.producer.model.entity.Dummy;
import ai.fetcher.playground.sqspoc.producer.outbound.queue.DummyEntityCreationEventSender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class SqsDummyEntityCreationEventSender implements DummyEntityCreationEventSender {

    @Value("${ai.fetcher.playground.sqspoc.producer.queues.dummy-creation-event.url}")
    private final String dummyCreationEventQueueUrl;
    private final SqsSender sqsSender;

    @Override
    public void sendDummyCreated(final Dummy dummy) {
        sqsSender.sendTo(dummyCreationEventQueueUrl, dummy);
    }
}
