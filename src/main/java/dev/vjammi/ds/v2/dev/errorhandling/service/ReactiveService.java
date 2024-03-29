package dev.vjammi.ds.v2.dev.errorhandling.service;

import dev.vjammi.ds.v2.dev.errorhandling.exception.UserTransformationException;
import dev.vjammi.ds.v2.dev.errorhandling.model.User;
import dev.vjammi.ds.v2.dev.errorhandling.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

public class ReactiveService {
    @Autowired
    UsersRepository repository;

    public ReactiveService(UsersRepository repository) {
        this.repository = repository;
    }

    /**
     * The onErrorContinue() operator
     * Recovers from errors by dropping the incriminating element from the sequence & continuing with subsequent element
     * This operator “swallows” the exception so it won’t propagate up the call chain/stack further
     * It also affects the behavior of onErrorResume() operators..
     * onErrorResume() is ignored if onErrorContinue() appears downstream
     * References
     *      https://www.dre.vanderbilt.edu/~schmidt/cs253/2021-PDFs/15.5.3.4-flux-error-operators.pdf
     *      https://devdojo.com/ketonemaniac/reactor-onerrorcontinue-vs-onerrorresume
     **/
    public Flux<User> getUsers() {
        Flux<User> users = repository.getUsers()
                .map(user -> transformUser(user))
                .onErrorResume(error -> {
                    if (error instanceof UserTransformationException){
                        User fallbackUser = getFallbackUser();
                        System.out.println(" Returning fallback User "+ fallbackUser);
                        return Flux.just(fallbackUser);
                    }else{
                        System.out.println(" Returning no fallback User ");
                        return Flux.empty();
                    }
                })
                .onErrorContinue((error, user) -> {
                    System.err.println(" User transformation error for "+ user);
                });
                //.filter(user -> Objects.nonNull(user));
        return users;
    }

    public User transformUser(User user) {
        try {
            return toUpper(user);
        } catch (Exception e) {
            throw Exceptions.propagate(e);
        }
    }

    public User getFallbackUser() {
        return new User("MIKE", "FALLBACK_BOT");
    }

    private User toUpper(User user) throws UserTransformationException {
        if (user.getName().equalsIgnoreCase("Kyle")) {
            throw new UserTransformationException("UserTransformationException for user ");
        }
        if (user.getName().equalsIgnoreCase("Dan")) {
            throw new IllegalArgumentException("IllegalArgumentException for user");
        }

        User transformedUser = new User(user.getName().toUpperCase(), user.getProfession().toUpperCase());
        return transformedUser;
    }

}
