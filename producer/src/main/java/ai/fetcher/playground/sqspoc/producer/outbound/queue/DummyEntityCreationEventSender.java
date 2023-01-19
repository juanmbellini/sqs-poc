package ai.fetcher.playground.sqspoc.producer.outbound.queue;

import ai.fetcher.playground.sqspoc.producer.model.entity.Dummy;

public interface DummyEntityCreationEventSender {

    void sendDummyCreated(final Dummy dummy);
}
