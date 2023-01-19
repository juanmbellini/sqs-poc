package ai.fetcher.playground.sqspoc.consumer.service;

import ai.fetcher.playground.sqspoc.consumer.model.dto.DummyEvent;
import ai.fetcher.playground.sqspoc.consumer.model.entity.Dummy;

public interface HelloWorldService {

    void helloWorld(final DummyEvent event);
}
