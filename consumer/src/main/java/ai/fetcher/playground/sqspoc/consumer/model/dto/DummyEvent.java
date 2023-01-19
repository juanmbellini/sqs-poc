package ai.fetcher.playground.sqspoc.consumer.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@ToString
public class DummyEvent {
    private final UUID id;
    private final String name;
    private final String email;
}
