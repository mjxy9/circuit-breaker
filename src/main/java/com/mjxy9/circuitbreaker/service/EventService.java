package com.mjxy9.circuitbreaker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class EventService {

    public String handleEvent() {

        if (1 == 1) {
            throw new RuntimeException("BAM");
        }
        return "This method should do something";
    }
}
