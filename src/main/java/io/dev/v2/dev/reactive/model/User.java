package io.dev.v2.dev.reactive.model;

public record User (String name, String profession) {
    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }
}
