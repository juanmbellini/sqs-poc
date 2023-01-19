package ai.fetcher.playground.sqspoc.producer.outbound.queue.impl;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@AllArgsConstructor
class SqsSender {

    private final QueueMessagingTemplate queueMessagingTemplate;


    public void sendTo(@NonNull final String queueUrl, @NonNull final Object object) {
        final var headers = buildHeaders();
        try {
            this.queueMessagingTemplate.convertAndSend(queueUrl, object, headers);
        } catch (final Exception e) {
            throw new RuntimeException(String.format("SQS queue %s", queueUrl), e);
        }
    }


    private static Map<String, Object> buildHeaders() {
        return Map.of(
                "message-group-id", UUID.randomUUID().toString(),
                "message-deduplication-id", UUID.randomUUID().toString()
        );
    }
}
