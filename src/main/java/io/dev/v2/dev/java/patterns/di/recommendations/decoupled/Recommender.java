package io.dev.v2.dev.java.patterns.di.recommendations.decoupled;

public class Recommender {

    // Use filter interface to select filter
    private final Filter filter;

    public Recommender(Filter filter) {
        super();
        this.filter = filter;
    }

    // Use a filter to find recommendations
    public String [] recommendMovies (String movie) {
        // Print the name of interface implementation being used
        System.out.println("Name of the filter in use: " + filter + "\n");
        String[] results = filter.getRecommendations("Finding Dory");
        return results;
    }

}