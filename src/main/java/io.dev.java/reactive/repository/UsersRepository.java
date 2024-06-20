package io.dev.java.reactive.repository;

import io.dev.java.reactive.model.User;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class UsersRepository {

    public Flux<User> getUsers() {
        //List<User> usersAsAnIterable = getUsersAsList();
        //return Flux.fromIterable(usersAsAnIterable)
        //        .delayElements(Duration.ofMillis(1000));

        Flux<User> userFlux = Flux.fromStream(() -> getUsersAsList().stream())
                .delayElements(Duration.ofMillis(1000));
        return userFlux;

    }

    private static List<User> getUsersAsList() {
        return Arrays.asList(new User("jhon", "SDE"),
                new User("Kyle", "bot"),
                new User("Mike", "SDE"),
                new User("Dan", "Eng."),
                new User("Jim", "Doc"));
    }
}
