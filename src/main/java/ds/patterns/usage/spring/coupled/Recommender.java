package ds.patterns.usage.spring.coupled;

public class Recommender {

    public String[] recommendMovies(String movie) {
        //use content based filter to find similar movies
        ContentBasedFilter filter = new ContentBasedFilter();
        String[] results = filter.getRecommendations("Finding Dory");
        //return the results
        return results;
    }
}