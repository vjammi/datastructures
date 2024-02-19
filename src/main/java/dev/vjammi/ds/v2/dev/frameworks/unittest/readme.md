### JUnit 5 modules
JUnit 5 is composed of several modules
    JUnit Jupiter
    JUnit Platform
    JUnit Vintage
        

### JUnit 5 Arch

    junit-jupiter-api            junit-vintage-api           custom-api
    junit-jupiter-engine         junit-vintage-engine        custom-engine-impl


                                JUnit Platform Engine API
                                JUnit Launcher API

                       IDEs                    Build Tools [Maven]
Note: 
    junit-jupiter-api module for the JUnit 5 tests 
    junit-vintage-api module for the JUnit 4 tests.

### Characteristics of a test class
A test class has the following characteristics:
    In JUnit 4, it’s mandatory that all test classes and methods have public access. 
    In JUnit 5, this restriction is relaxed because the framework uses reflection to discover the test classes and methods. We should have default access.
    Java creates a no-argument constructor implicitly for each class.
    It should have at least one test method.

### Characteristics of a test method#
    It should have the org.junit.jupiter.api.Test annotation.
    It should return void.
    It should have parameters.
    We can add an name our tests using @DisplayName

### Structuring Tests - Using Approach-Act-Assert 
    In the Approach phase, we initialize the piece of the unit we want to test. This means we create the required objects and test data.
    In the Act phase, we execute the code to be verified. This is usually a method or a function.
    In the Assert phase, we verify whether the behavior of the method matches our expectations.

### Arrange-Act-Assert vs Given-When-Then
The Arrange-Act-Assert pattern describes what the test code is doing, whereas Given-When-Then reason in terms of what user action the test is describing.
Given-When-Then is generally preferred for acceptance tests.

### JUnit 4 and JUnit 5 comparison
    JUnit 5         JUnit 4         
    -----------------------
    @Test           @Test
    @BeforeEach     @Before
    @BeforeAll      @BeforeClass
    @AfterEach      @After
    @AfterAll       @AfterClass
    @Disabled       @Ignore
    @Tag            @Category
    @ExtendWith     @RunWith

#### JUnit4
- We define the test with the @Test annotation from the package 
      org.junit
- We use the annotations @BeforeClass, @Before, @After, and @AfterClass to hook into the life cycle of the JUnit 4 tests.
- We use the @Ignore annotation to skip the execution of a given test.

#### JUnit5
- We define the test with the @Test annotation from the package 
      org.junit.jupiter.api
- We use the annotations @BeforeAll, @BeforeEach, @AfterEach, and @AfterAll to hook into the life cycle of the JUnit5 tests.
- The @Disabled annotation is used to skip the execution of a given test.


###  Structuring Tests Using the AAA
```
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    @DisplayName("Test Integer Addition")
    void shouldTestIntegerAddition() {

        // Arrange
        Calculator calculator = new Calculator();

        // Act
        int result = calculator.add(1, 2);

        // Assert
        assertEquals(3, result);
    }
}
```

### Assertions
Assertions in JUnit5 are static methods used to verify the behavior of our test. These methods are provided by the class 
    org.junit.jupiter.api.Assertions

1. assertEquals: Assert that expected and actual values are equal.
2. assertNotEquals: Assert that expected and actual values are not equal.
3. assertTrue: Assert that condition is true.
4. assertFalse: Assert that condition is false.
5. assertNull: Assert that object is null.
6. assertNotNull: Assert that object is not null.
7. assertSame: Assert that expected and actual values refer to the same object.
8. assertNotSame: Assert that expected and actual values do not refer to the same object.
9. assertArrayEquals: Assert that expected and actual arrays are equals.
10. assertThrows: Assert that execution of the supplied code throws an exception.

### Assumptions
The following are some methods that are part of the class
    org.junit.jupiter.api.Assumptions

1. assumeTrue
2. assumeFalse
3. assumingThat

### JUnit 5 Life Cycle
The following are the three phases involved as parts of the test life cycle:
- Setup 
  The test infrastructure and the required configuration is prepared. This setup is prepared at two levels.
  The first one is at the class level where we can setup/configure a resource like a Database Connection and insert required Test Data into the database. This phase runs only once per Test class.
  The second one is at the test level, where we initialize the required objects for the test execution. This allows us to remove duplicated code inside the tests. This phase runs before every test method.

- Execution
  The tests are executed and verified. We mark the test as a success or a failure based on this.

- Cleanup
  We have the below two levels of cleanup:
  Test-level cleanup is where we perform some cleanup activities after every test method is executed.
  Class-level cleanup is where we cleanup or release the resources that are configured as a part of the class level setup phase. This phase runs only once per test class.

#### Test life cycle annotations in JUnit 5
JUnit 5 provides the following set of annotations for each phase of the test life cycle:
- @BeforeAll (Class-level setup)
  The method with this annotation is executed before any of the @Test methods inside the class.
- @BeforeEach (Test-level setup)
  The method with this annotation is executed before each @Test method.
- @AfterEach (Test level cleanup)
  The method with this annotation is executed after each @Test method.
- @AfterAll (Class level cleanup)
  The method with this annotation is executed after all the @Test methods inside the class.

### Nested Tests
### Tagged Tests
### Repeated Tests
### Parameterized Tests
### Disabled Tests

### Assertion Libraries
AssertJ
Hamcrest


### Exceptions handling in JUnit4 and JUnit5
@Test(expected = InvalidInputException.class)
public void transformInput(){
    ...
    subject.invokeSomeMethod(someInput);
    ...
}

public void transformInput(){
    assertThrows( InvalidInputException.class,  () -> subject.invokeSomeMethod(someInput));
}

### @RunWith(...) Vs @ExtendWith(...)

@RunWith(MockitoJUnitRunner.class)
public class SomeClassTest{
    ...
}

@ExtendWith(MockitoJUnitRunner.class)
public class SomeClassTest{
    ...
}