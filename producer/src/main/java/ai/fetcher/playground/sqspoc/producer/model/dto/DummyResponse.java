package ai.fetcher.playground.sqspoc.producer.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DummyResponse {
    private final UUID id;
    private final String name;
    private final String email;
}
