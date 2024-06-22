package io.dev.v2.dev.java.patterns.di.recommendations.decoupled;


public class ContentBasedFilter implements Filter{

    public String[] getRecommendations(String movie) {

        //implement logic of content based filter

        //return movie recommendations
        return new String[] {"Happy Feet", "Ice Age", "Shark Tale"};
    }

}