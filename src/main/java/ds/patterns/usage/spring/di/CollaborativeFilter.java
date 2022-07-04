package ds.patterns.usage.spring.di;

//public class CollaborativeFilter implements Filter {
//
//    public String[] getRecommendations(String movie) {
//        //logic of content based filter
//        return new String[] {"Finding Nemo", "Ice Age", "Toy Story"};
//    }
//}

import org.springframework.stereotype.Component;

public class CollaborativeFilter implements Filter{
    public String[] getRecommendations(String movie) {
        //logic of collaborative filter
        return new String[] {"Finding Nemo", "Ice Age", "Toy Story"};
    }
}