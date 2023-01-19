package ai.fetcher.playground.sqspoc.consumer.service.impl;

import ai.fetcher.playground.sqspoc.consumer.model.dto.DummyEvent;
import ai.fetcher.playground.sqspoc.consumer.service.HelloWorldService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public void helloWorld(@NonNull final DummyEvent event) {
        log.info("Received event: {}", event);
    }
}
