package dev.vjammi.ds.v2.dev.java.patterns.di.recommendations.coupled;

public class Recommender {

    public String[] recommendMovies(String movie) {
        //use content based filter to find similar movies
        ContentBasedFilter filter = new ContentBasedFilter();
        String[] results = filter.getRecommendations("Finding Dory");
        //return the results
        return results;
    }
}