package dev.vjammi.ds.v2.dev.java.patterns.di.recommendations.decoupled;

import java.util.Arrays;

public class RecommenderSystemApplication {

    //    public static void main1(String[] args) {
    //        RecommenderImplementation recommender = new RecommenderImplementation();
    //        String[] result = recommender.recommendMovies("Finding Dory");
    //        System.out.println(Arrays.toString(result));
    //    }

    public static void main(String[] args) {
        //passing name of the filter as constructor argument
        Recommender recommender = new Recommender(new ContentBasedFilter());
        //RecommenderImplementation recommender = new RecommenderImplementation(new CollaborativeFilter());

        //call method to get recommendations
        String[] result = recommender.recommendMovies("Finding Dory");

        //display results
        System.out.println(Arrays.toString(result));
    }
}