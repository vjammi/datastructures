package io.dev.v2.dev.reactive;

import io.dev.v2.dev.reactive.controller.ReactiveApiController;
import io.dev.v2.dev.reactive.model.User;
import io.dev.v2.dev.reactive.repository.UsersRepository;
import io.dev.v2.dev.reactive.service.ReactiveService;
import reactor.core.publisher.Flux;

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
