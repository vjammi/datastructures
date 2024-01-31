package dev.vjammi.ds.v2.dev.pattern.strategy.search;

public class GenericRequestValidationBehavior implements RequestValidationBehavior {
    @Override
    public String validate(String query) {
        String result = query + " - GenericRequestValidationBehavior - valid";
        System.out.println(result);
        return result;
    }
}
