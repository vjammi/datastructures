package dev.vjammi.ds.v2.dev.frameworks.di.recommendations.di;

//public class CollaborativeFilter implements Filter {
//
//    public String[] getRecommendations(String movie) {
//        //logic of content based filter
//        return new String[] {"Finding Nemo", "Ice Age", "Toy Story"};
//    }
//}

import org.springframework.stereotype.Component;

// Indicates that an annotated class is a "component". Such classes are considered as candidates for auto-detection
// when using annotation-based configuration and classpath scanning.
@Component
public class CollaborativeFilter implements Filter{
    public String[] getRecommendations(String movie) {
        //logic of collaborative filter
        return new String[] {"Finding Nemo", "Ice Age", "Toy Story"};
    }
}