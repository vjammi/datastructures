### Functional Programming
Intent of functional/declarative programming is readability.
1. Being declarative
2. Prefer pure functions. Functions are pure. No side effect
3. Immutability

### Functional Programming
1. Assignment less programming

2. High Order functions [Functions are first class citizens]
   Where functions are first class citizens just like objects 
   - So far we have object composition
       - Pass object to a function 
       - Create object within a function.
       - Return object from a function. 
   - With functional programming we can do functional composition we can 
     - Pass function to a function
     - Create function within a function
     - Return function from a function
  
3. Functions are pure
   - Pure functions only have an effect but no side effects
   - Does not make any changes to its surroundings
   - Also, does not depend on anything that changes
   - Pure functions are lazy, lazily evaluated. 
   - If it depends on anything dependent

### Functional style vs Purely functional programming
- Purely functional programming [haskel] will not allow any mutability
- Functional style [like java] allows mutability and provides higher order functions

### What does it mean to code with functional style?
- State transformations instead of state mutations
  - A method is a function which belongs to a class or to an object 
  - Take the data transform through the states, instead of mutating the states
  - A method in java often belongs to a class [static] or an object [public/private]
  - A function has 4 things
    1. Function name
    2. Return type
    3. List of parameters
    4. Body of a function
- What's a Lambda expression?
  - Out of the 4 things above, the 2 most important things of a function are
    - #4 [Body of the function] 
    - #3 [List of parameters to be able to pass data to it]
      (parameter list) -> body
  - What about the return type then? 
    - The return type is inferred
  - That is what a lambda expression is. 
    - It is an anonymous function with a parameter list and body.
- Refactoring from external to internal iterator
  - See the sample progression in externalToInternalIteratorProgression()

- Fundamental shift from imperative to functional style
- Mapping operations - Eliminating mutability

- Declarative - Expressive. Tell me what, not How
- Imperative      vs        Declarative
  - How                     What
  - Mutate                  Transform
  - Side-effect             Pure [only effect]
  - Pass Objects            Not only pass objects but functions too
  - Hard to Compose         Functional Composition

- Lambdas vs Closures
  - See example... 
  - Effectively final - a local variable within a method is effectively final

- Lazy Evaluation or Deferred Execution
  - What polymorphism is to OO programming, lazy evaluation is to Functional programming. 
  - As Polymorphism is interdependent on abstraction and encapsulation. Lazy evaluation cannot be possible without immutability.
  - Since it is a pure function, since it does not modify anything or depend on anything - so we can run it now or later or never run it.
  - Impure fns run eagerly.
  - So we cannot evaluate lazily without being immutable.
- Composing with lambda expressions
  - Since we are now able to evaluate a func lazily, we can now do func pipelining or composition.
  - Until we hit the terminal operator, intermediate operators are not executed.   
  
- Benefits of the newer approach
  - code clarity???
  - fewer errors - no accidental complexity, remove extra variables, and no mutability
  - easier parallelization - 


------------------------------------------------------------------------------------------------------------------------
Imperative
Shared/ Mutable state is common in imperative OO programming
State is some data maintained over time starting some initial value
State is mutable if it can be modified or in other words it can be reassigned after its been reinitialized
State is shared -  If multiple threads can modify the same instance of object simultaneously
Then those different threads can access and/or modify the instance simultaneously
Can cause hazards when called in an unsyncronized way and needs to be avoided in concurrent and parallel programming

Functional
Discourages state changes and shared mutable state to avoid these hazards (??? side effects)
Understanding the effects of a method that uses shared state requires
knowing the entire history of every shared variable that the method uses or effects
Now changing the order these methods are called can cause cascadding failures
Shared mutable state is specially in parallel and conecurrent programming

Declarative

Functional
Composition using Lambda functions
Computations are treated as evaluating math functions ythat can be composed together into a pipeline

State - Immutable / SideEffects
  Minimizing or ideally eliminating state changes and mutable shared state
  
  
Immutablity
  final
  
Pure funtions
Side Effects
  
Composing Functions
  Functional Composition
  Lambda Calulus
  Streams - Pipeline of operations that can be used to process a sequence of elements
  Operations
      Intermediate opoerations
      Short Circuiting operations
      Terminal operations
      
      public void checkForPrimes(){
      
          new Random().ints(1, Integer.MAX_VALUE)
              .filter(num::isOdd)			// Only allow odd nums. Using isOdd method reference
              .mapToObj( num::isPrime )   // Using isPrime method reference
              .limit(200) 				// Apply the limit intermediate operation to short ciruit after 200 odd nums
              .forEach(num::printResult); // forEach terminal operation, which supports side effects intentionally. 
                                          // Terminates the stream and prints the results
      }
  
  Notice how Java Streams apply the "Fluent interface" pattern that chains method calls by connecting output of one intermediate operation, as input to the other intermediate operation
  
      

