package ai.fetcher.playground.sqspoc.producer.controller.rest;

import ai.fetcher.playground.sqspoc.producer.model.dto.DummyRequest;
import ai.fetcher.playground.sqspoc.producer.model.dto.DummyResponse;
import ai.fetcher.playground.sqspoc.producer.service.HelloWorldService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/hello/world")
public class HelloWorldEndpoint {

    private final HelloWorldService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DummyResponse> helloWorld(@RequestBody final DummyRequest request) {
        final var dummy = service.helloWorld(request);

        final var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dummy.getId());
        return ResponseEntity.created(location.toUri()).body(dummy);

    }

}
