package io.dev.java.reactive;


import io.dev.java.reactive.controller.ReactiveApiController;
import io.dev.java.reactive.model.User;
import io.dev.java.reactive.repository.UsersRepository;
import io.dev.java.reactive.service.ReactiveService;
import reactor.core.publisher.Flux;

public class ReactiveApiClient {
    public static void main(String[] args) throws InterruptedException {
        ReactiveApiController controller = new ReactiveApiController(new ReactiveService(new UsersRepository()));
        Flux<User> users = controller.handleRequest();
        users.subscribe(data -> System.out.println(data),
                        err -> System.out.println(err),
                        () -> System.out.println("DONE"));

        Thread.sleep(10000);
    }
}
