package io.dev.v2.dev.reactive.repository;

import io.dev.v2.dev.reactive.model.User;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class UsersRepository {

    public Flux<User> getUsers() {
        List<User> usersAsAnIterable = getUsersAsList();
        return Flux.fromIterable(usersAsAnIterable)
                .delayElements(Duration.ofMillis(1000));
    }

    private static List<User> getUsersAsList() {
        return Arrays.asList(new User("jhon", "SDE"),
                new User("Kyle", "bot"),
                new User("Mike", "SDE"),
                new User("Dan", "Eng."),
                new User("Jim", "Doc"));
    }
}
