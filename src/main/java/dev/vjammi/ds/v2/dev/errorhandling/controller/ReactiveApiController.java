package dev.vjammi.ds.v2.dev.errorhandling.controller;

import dev.vjammi.ds.v2.dev.errorhandling.model.User;
import dev.vjammi.ds.v2.dev.errorhandling.service.ReactiveService;
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
