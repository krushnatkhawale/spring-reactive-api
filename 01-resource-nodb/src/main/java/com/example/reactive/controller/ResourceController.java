package com.example.reactive.controller;

import com.example.reactive.model.Resource;
import com.example.reactive.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@RestController
public class ResourceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @GetMapping("/resources/{id}")
    public Mono<Resource> getResource(@PathVariable Long id) {
        LOGGER.info("getResource: /resources/{}", id);
        return Mono.just(ResourceUtil.makeResource(id));
    }

    @GetMapping(path = "/resources", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Resource> getResources() {
        LOGGER.info("getResources: /resources");
        return getAllResources();
    }

    @GetMapping(path = "/resourcesNonReactive")
    public Flux<Resource> getResourcesNotReactive() {
        LOGGER.info("getResourcesNotReactive: /resourcesNonReactive");
        return getAllResources();
    }

    private Flux<Resource> getAllResources() {
        int noOfResources = getNoOfResources();
        LOGGER.info("Returning {} resources", noOfResources);
        return Flux.range(1, noOfResources)
                .map(Long::new)
                .map(ResourceUtil::makeResource);
    }

    private int getNoOfResources() {
        return new Random().nextInt(25)+5;
    }
}