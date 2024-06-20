package dev.vjammi.ds.v2.dev.reactive;

import dev.vjammi.ds.v2.dev.errorhandling.controller.ReactiveApiController;
import dev.vjammi.ds.v2.dev.errorhandling.model.User;
import dev.vjammi.ds.v2.dev.errorhandling.repository.UsersRepository;
import dev.vjammi.ds.v2.dev.errorhandling.service.ReactiveService;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class ReactiveApiClient {
    public static void main(String[] args) throws InterruptedException {
        ReactiveApiController controller = new ReactiveApiController(new ReactiveService(new UsersRepository()));
        Flux<User> users = controller.handleRequest();
        users.subscribe(x -> {
            System.out.println(x);
        });
        Thread.sleep(10000);
    }
}
