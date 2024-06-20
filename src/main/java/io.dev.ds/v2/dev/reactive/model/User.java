package dev.vjammi.ds.v2.dev.errorhandling.model;

public record User (String name, String profession) {
    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }
}
