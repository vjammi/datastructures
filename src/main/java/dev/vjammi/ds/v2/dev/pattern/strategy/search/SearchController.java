package dev.vjammi.ds.v2.dev.pattern.strategy.search;

public class SearchController {
    private Api1SearchService searchService;
    public SearchController() {
        this.searchService = new Api1SearchService(new GenericRequestValidationBehavior(), new Api1FullTextDynamoDbSearchStrategy(), new Api1DynamodbDynamoDbSearchStrategy());
    }
    public String instructionsSearchQuery(String query){
        System.out.println("Request " +query);
        String response = searchService.search(query);
        System.out.println("Response "+response);
        return response;
    }

    public static void main(String[] args) {
        new SearchController().instructionsSearchQuery("id:1234567890");
    }

}
