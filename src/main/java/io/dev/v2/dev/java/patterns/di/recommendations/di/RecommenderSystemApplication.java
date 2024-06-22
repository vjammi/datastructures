package io.dev.v2.dev.java.patterns.di.recommendations.di;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RecommenderSystemApplication {

    //public static void main(String[] args) {
    //    //passing name of the filter as constructor argument
    //    dev.vj.patterns.usage.spring.decoupled.Recommender recommender = new dev.vj.patterns.usage.spring.decoupled.Recommender(new ContentBasedFilter());
    //    //RecommenderImplementation recommender = new RecommenderImplementation(new CollaborativeFilter());
    //
    //    //call method to get recommendations
    //    String[] result = recommender.recommendMovies("Finding Dory");
    //
    //    //display results
    //    System.out.println(Arrays.toString(result));
    //}

    public static void main(String[] args) {
        //ApplicationContext manages the beans and dependencies
        ApplicationContext appContext = SpringApplication.run(RecommenderSystemApplication.class, args);

        //Use ApplicationContext to find which filter is being used
        Recommender recommender = appContext.getBean(Recommender.class);

        //call method to get recommendations
        String[] result = recommender.recommendMovies("Finding Dory");

        //display results
        System.out.println(Arrays.toString(result));
    }
}