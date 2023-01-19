package ai.fetcher.playground.sqspoc.producer.service;

import ai.fetcher.playground.sqspoc.producer.model.dto.DummyRequest;
import ai.fetcher.playground.sqspoc.producer.model.dto.DummyResponse;

public interface HelloWorldService {

    DummyResponse helloWorld(final DummyRequest request);
}
