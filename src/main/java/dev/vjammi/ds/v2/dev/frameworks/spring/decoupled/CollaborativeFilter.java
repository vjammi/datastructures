package dev.vjammi.ds.v2.dev.frameworks.spring.decoupled;

public class CollaborativeFilter implements Filter{

    public String[] getRecommendations(String movie) {
        //logic of content based filter
        return new String[] {"Finding Nemo", "Ice Age", "Toy Story"};
    }
}