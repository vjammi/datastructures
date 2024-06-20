package io.dev.java.lambda;

public class MyLambdaUsage {

    public static void main(String[] args) {

        MyLambdaFunction lambda = () -> System.out.printf("MyLambdaFunction...");
        lambda.foo();

        // Inline implementation of an interface / inner class
        // In most of the cases we may say that the Lambda fn is a fancy way of making an anonymous inner class.
        // Not completely true but close. There are differences.
        // Reasoning: Backward Compatibility - now all the old apis are which we have accessed via inner class, now we are able to access them as lambda expressions
        AddLambdaFunction addInstance = new AddLambdaFunction(){
            @Override
            public int add(int a, int b) {
                return a + b;
            }
        };

        int add = addInstance.add(6, 8);
        System.out.println();

        // A lambda interface of type AddLambdaFunction
        AddLambdaFunction addLambdaFunction = (int a, int b) -> a + b;
        int sum1 = addLambdaFunction.add(4, 5);
        System.out.println(sum1);

        MyLambdaUsage usage = new MyLambdaUsage();

        // Option 1
        int sum2 = usage.passLambdaFn(addLambdaFunction);
        System.out.println("Sum: " + sum2);

        // Option 2
        int product = usage.passLambdaFn((int a, int b) -> a * b);
        System.out.println("Product: " +product);

        int sum3 = usage.passLambdaFn((int a, int b) -> a + b);
        System.out.println("Sum "+ sum3);
    }

    private int passLambdaFn(AddLambdaFunction addLambdaFunction) {
        int result = addLambdaFunction.add(4, 5);
        System.out.println("result " + result);
        return result;
    }
}
