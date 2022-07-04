package ds.patterns.usage.spring.decoupled;


public interface Filter {
    public String[] getRecommendations(String movie);
}