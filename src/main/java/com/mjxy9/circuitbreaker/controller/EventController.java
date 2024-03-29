package com.mjxy9.circuitbreaker.controller;

import com.mjxy9.circuitbreaker.service.EventService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final CircuitBreaker circuitBreaker;

    @GetMapping("/api/v1/events")
    public ResponseEntity<String> getEvents() {

        final Supplier<String> decoratorSupplier = CircuitBreaker
                .decorateSupplier(
                        circuitBreaker,
                        eventService::handleEvent);

        final String result = Try.ofSupplier(decoratorSupplier)
                .recover(this.recoverFromError()).get();

        return ResponseEntity.ok(result);
    }

    public Function<Throwable, String> recoverFromError() {

        return Throwable::getMessage;
    }
}
