package ai.fetcher.playground.sqspoc.producer.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Dummy {
    private final UUID id;
    private final String name;
    private final String email;
}
