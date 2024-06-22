package io.dev.v2.dev.java.patterns.di.recommendations.di;

//public class ContentBasedFilter implements Filter {
//    public String[] getRecommendations(String movie) {
//        //implement logic of content based filter
//        //return movie recommendations
//        return new String[] {"Happy Feet", "Ice Age", "Shark Tale"};
//    }
//}


public class ContentBasedFilter implements Filter{
    //getRecommendations takes a movie as input and returns a list of similar movies
    public String[] getRecommendations(String movie) {
        //implement logic of content based filter
        //return movie recommendations
        return new String[] {"Happy Feet", "Ice Age", "Shark Tale"};
    }
}