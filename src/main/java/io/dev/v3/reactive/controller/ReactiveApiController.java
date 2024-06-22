package io.dev.v3.reactive.controller;


import io.dev.v3.reactive.model.User;
import io.dev.v3.reactive.service.ReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

public class ReactiveApiController {
    @Autowired
    private ReactiveService service;
    public ReactiveApiController(ReactiveService service) {
        this.service = service;
    }
    public Flux<User> handleRequest() {
         Flux<User> users = service.getUsers();
         return users;
    }
}
