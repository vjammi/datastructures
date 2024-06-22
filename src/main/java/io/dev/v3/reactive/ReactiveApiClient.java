package io.dev.v3.reactive;


import io.dev.v3.reactive.controller.ReactiveApiController;
import io.dev.v3.reactive.model.User;
import io.dev.v3.reactive.repository.UsersRepository;
import io.dev.v3.reactive.service.ReactiveService;
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
