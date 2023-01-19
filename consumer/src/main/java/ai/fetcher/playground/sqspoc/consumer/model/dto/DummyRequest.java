package ai.fetcher.playground.sqspoc.consumer.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DummyRequest {
    private final String name;
    private final String email;
}
