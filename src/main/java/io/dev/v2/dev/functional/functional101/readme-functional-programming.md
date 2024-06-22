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
