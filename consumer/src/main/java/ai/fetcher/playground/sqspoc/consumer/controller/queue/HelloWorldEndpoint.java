package ai.fetcher.playground.sqspoc.consumer.controller.queue;

import ai.fetcher.playground.sqspoc.consumer.exception.BadRequestException;
import ai.fetcher.playground.sqspoc.consumer.model.dto.DummyEvent;
import ai.fetcher.playground.sqspoc.consumer.service.HelloWorldService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class HelloWorldEndpoint {

    private final ObjectMapper objectMapper;
    private final HelloWorldService helloWorldService;

    @SqsListener(
            value = "${ai.fetcher.playground.sqspoc.consumer.queues.dummy-creation-event.url}",
            deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS
    )
    public void helloWorld(final String message) {
        if (StringUtils.isNotBlank(message)) {
            processMessage(message);
        }
    }


    private void processMessage(final String message) {
        try {
            final var event = parse(message);
            helloWorldService.helloWorld(event);
        } catch (final RuntimeException e) {
            log.error(
                    String.format(
                            "Skipping sync message \"%s\", as unrecoverable error occurred. Exception message: \"%s\"",
                            message,
                            e.getMessage()
                    ), e
            );
        } catch (final Exception e) {
            log.warn(
                    String.format(
                            "Could not process sync message \"%s\", but we will try again. Exception message: %s",
                            message,
                            e.getMessage()
                    ), e
            );
            throw e;
        }
    }

    private DummyEvent parse(final String message) {
        try {
            return objectMapper.readValue(message, DummyEvent.class);
        } catch (final JsonProcessingException e) {
            throw new BadRequestException(String.format("Cannot parse message %s into a UserSyncEvent", message), e);
        }
    }
}
