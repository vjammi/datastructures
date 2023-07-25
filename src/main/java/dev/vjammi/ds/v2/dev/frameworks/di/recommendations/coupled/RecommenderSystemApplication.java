package dev.vjammi.ds.v2.dev.frameworks.di.recommendations.coupled;

import java.util.Arrays;

public class RecommenderSystemApplication {

    public static void main(String[] args) {
        Recommender recommender = new Recommender();
        String[] result = recommender.recommendMovies("Finding Dory");
        System.out.println(Arrays.toString(result));
    }

}