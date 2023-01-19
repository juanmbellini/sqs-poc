package ai.fetcher.playground.sqspoc.producer.service.impl;

import ai.fetcher.playground.sqspoc.producer.model.dto.DummyRequest;
import ai.fetcher.playground.sqspoc.producer.model.dto.DummyResponse;
import ai.fetcher.playground.sqspoc.producer.model.entity.Dummy;
import ai.fetcher.playground.sqspoc.producer.outbound.queue.DummyEntityCreationEventSender;
import ai.fetcher.playground.sqspoc.producer.service.HelloWorldService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class HelloWorldServiceImpl implements HelloWorldService {

    private final DummyEntityCreationEventSender dummyEntityCreationEventSender;

    @Override
    public DummyResponse helloWorld(@NonNull final DummyRequest request) {
        final var dummy = Dummy.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .email(request.getEmail())
                .build();
        dummyEntityCreationEventSender.sendDummyCreated(dummy);
        return DummyResponse.builder()
                .id(dummy.getId())
                .name(dummy.getName())
                .email(dummy.getEmail())
                .build();
    }
}
