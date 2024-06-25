## Mockito 
https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#1
Main reference documentation features:
- mock()/@Mock: create mock
    - optionally specify how it should behave via Answer/MockSettings
    - when()/given() to specify how a mock should behave
    - If the provided answers don’t fit your needs, write one yourself extending the Answer interface
- spy()/@Spy: partial mocking, real methods are invoked but still can be verified and stubbed
- @InjectMocks: automatically inject mocks/spies fields annotated with @Spy or @Mock
- verify(): to check methods were called with given arguments
    - can use flexible argument matching, for example any expression via the any()
    - or capture what arguments were called using @Captor instead
- Try Behavior-Driven development syntax with BDDMockito

Remember
    Do not mock types you don’t own
        https://testing.googleblog.com/2020/07/testing-on-toilet-dont-mock-types-you.html
    Don’t mock value objects  
        https://stackoverflow.com/questions/47304193/don-t-mock-value-objects-too-generic-rule-without-explanation
    Don’t mock everything
    Show love with your tests!

### Declaring dependency on “mockito-core” library
```
repositories { mavenCentral() }
dependencies { testImplementation "org.mockito:mockito-core:3.+" }
```
### 1. We can verify some behaviors / interactions 
```
    import static org.mockito.Mockito.*;
    
    // mock creation
    List mockedList = mock(List.class);
    // or even simpler with Mockito 4.10.0+
    // List mockedList = mock();
    
    // using mock object - it does not throw any "unexpected interaction" exception
    mockedList.add("one");
    mockedList.clear();
    
    // selective, explicit, highly readable verification
    verify(mockedList).add("one");
    verify(mockedList).clear();
    
    // --------------------------------------------------
            
    //Let's import Mockito statically so that the code looks clearer
    import static org.mockito.Mockito.*;
    
    //mock creation
    List mockedList = mock(List.class);
    
    //using mock object
    mockedList.add("one");
    mockedList.clear();
    
    //verification
    verify(mockedList).add("one");
    verify(mockedList).clear();    
```

### 2. We can stub method calls
```
     //You can mock concrete classes, not just interfaces
     LinkedList mockedList = mock(LinkedList.class);
    
     //stubbing
     when(mockedList.get(0)).thenReturn("first");
     when(mockedList.get(1)).thenThrow(new RuntimeException());
    
     //following prints "first"
     System.out.println(mockedList.get(0));
    
     //following throws runtime exception
     System.out.println(mockedList.get(1));
    
     //following prints "null" because get(999) was not stubbed
     System.out.println(mockedList.get(999));
    
     //Although it is possible to verify a stubbed invocation, usually it's just redundant
     //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
     //If your code doesn't care what get(0) returns, then it should not be stubbed.
     verify(mockedList).get(0);
```

### 3. Argument matchers

### 4. Verifying exact number of invocations / at least x / never 

### 5. Stubbing void methods with exceptions Link icon
```
   doThrow(new RuntimeException()).when(mockedList).clear();
   //following throws RuntimeException:
   mockedList.clear();
```
Read more about doThrow()|doAnswer() family of methods in section 12.

### 6. Verification in order Link icon

### 10. Stubbing consecutive calls (iterator-style stubbing) Link icon
```
    Sometimes we need to stub with different return value/exception for the same method call. Typical use case could be mocking iterators. Original version of Mockito did not have this feature to promote simple mocking. For example, instead of iterators one could use Iterable or simply collections. Those offer natural ways of stubbing (e.g. using real collections). In rare scenarios stubbing consecutive calls could be useful, though:   
        when(mock.someMethod("some arg"))
           .thenThrow(new RuntimeException())
           .thenReturn("foo");
         //First call: throws runtime exception:
         mock.someMethod("some arg");
         //Second call: prints "foo"
         System.out.println(mock.someMethod("some arg"));
         //Any consecutive call: prints "foo" as well (last stubbing wins).
         System.out.println(mock.someMethod("some arg"));
     
    Alternative, shorter version of consecutive stubbing:    
         when(mock.someMethod("some arg"))
           .thenReturn("one", "two", "three");
     
    Warning : if instead of chaining .thenReturn() calls, multiple stubbing with the same matchers or arguments is used, then each stubbing will override the previous one:    
         //All mock.someMethod("some arg") calls will return "two"
         when(mock.someMethod("some arg"))
           .thenReturn("one")
         when(mock.someMethod("some arg"))
           .thenReturn("two")
```
### 11. Stubbing with callbacks Link icon
Allows stubbing with generic Answer interface.
Yet another controversial feature which was not included in Mockito originally. We recommend simply stubbing with thenReturn() or thenThrow(), which should be enough to test/test-drive any clean and simple code. However, if you do have a need to stub with the generic Answer interface, here is an example:
```
    
     when(mock.someMethod(anyString())).thenAnswer(
         new Answer() {
             public Object answer(InvocationOnMock invocation) {
                 Object[] args = invocation.getArguments();
                 Object mock = invocation.getMock();
                 return "called with arguments: " + Arrays.toString(args);
             }
     });

     //Following prints "called with arguments: [foo]"
     System.out.println(mock.someMethod("foo"));
```

### 12. doReturn()|doThrow()| doAnswer()|doNothing()|doCallRealMethod() family of methods 
```
Stubbing void methods requires a different approach from when(Object) because the compiler does not like void methods inside brackets...
Use doThrow() when you want to stub a void method with an exception:

   doThrow(new RuntimeException()).when(mockedList).clear();

   //following throws RuntimeException:
   mockedList.clear();
 
You can use doThrow(), doAnswer(), doNothing(), doReturn() and doCallRealMethod() in place of the corresponding call with when(), for any method. It is necessary when you    
    stub void methods
    stub methods on spy objects (see below)
    stub the same method more than once, to change the behaviour of a mock in the middle of a test.
but you may prefer to use these methods in place of the alternative with when(), for all of your stubbing calls.

Read more about these methods:
    doReturn(Object)   
    doThrow(Throwable...)
    doThrow(Class)
    doAnswer(Answer)
    doNothing()
    doCallRealMethod()
```

13. Spying on real objects
You can create spies of real objects. When you use the spy then the real methods are called (unless a method was stubbed).
Real spies should be used carefully and occasionally, for example when dealing with legacy code.
Spying on real objects can be associated with "partial mocking" concept. Before the release 1.8, Mockito spies were not real partial mocks. 
The reason was we thought partial mock is a code smell. At some point we found legitimate use cases for partial mocks (3rd party interfaces, interim refactoring of legacy code).
```
   List list = new LinkedList();
   List spy = spy(list);

   //optionally, you can stub out some methods:
   when(spy.size()).thenReturn(100);

   //using the spy calls *real* methods
   spy.add("one");
   spy.add("two");

   //prints "one" - the first element of a list
   System.out.println(spy.get(0));

   //size() method was stubbed - 100 is printed
   System.out.println(spy.size());

   //optionally, you can verify
   verify(spy).add("one");
   verify(spy).add("two");
```
##### Important gotcha on spying real objects! Link icon
Sometimes it's impossible or impractical to use when(Object) for stubbing spies. Therefore when using spies please consider doReturn|Answer|Throw() family of methods for stubbing. Example:

    List list = new LinkedList();
    List spy = spy(list);
    
    //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
    when(spy.get(0)).thenReturn("foo");
    
    //You have to use doReturn() for stubbing
    doReturn("foo").when(spy).get(0);

Mockito *does not* delegate calls to the passed real instance, instead it actually creates a copy of it. So if you keep the real instance and interact with it, don't expect the spied to be aware of those interaction and their effect on real instance state. The corollary is that when an *un-stubbed* method is called *on the spy* but *not on the real instance*, you won't see any effects on the real instance.
Watch out for final methods. Mockito doesn't mock final methods so the bottom line is: when you spy on real objects + you try to stub a final method = trouble. Also you won't be able to verify those method as well.


### 15. Capturing arguments for further assertions
Mockito verifies argument values in natural java style: by using an equals() method. This is also the recommended way of matching arguments because it makes tests clean and simple. In some situations though, it is helpful to assert on certain arguments after the actual verification. For example:

```
    ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
    verify(mock).doSomething(argument.capture());
    assertEquals("John", argument.getValue().getName());
```
Warning: it is recommended to use ArgumentCaptor with verification but not with stubbing. Using ArgumentCaptor with stubbing may decrease test readability because captor is created outside of assert (aka verify or 'then') block. Also it may reduce defect localization because if stubbed method was not called then no argument is captured.
In a way ArgumentCaptor is related to custom argument matchers (see javadoc for ArgumentMatcher class). Both techniques can be used for making sure certain arguments were passed to mocks. However, ArgumentCaptor may be a better fit if:
- custom argument matcher is not likely to be reused
- you just need it to assert on argument values to complete verification
Custom argument matchers via ArgumentMatcher are usually better for stubbing.

### 16. Real partial mocks (Since 1.8.0) Link icon
Finally, after many internal debates and discussions on the mailing list, partial mock support was added to Mockito. Previously we considered partial mocks as code smells. However, we found a legitimate use case for partial mocks.
Before release 1.8 spy() was not producing real partial mocks and it was confusing for some users. Read more about spying: here or in javadoc for spy(Object) method.


    //you can create partial mock with spy() method:
    List list = spy(new LinkedList());

    //you can enable partial mock capabilities selectively on mocks:
    Foo mock = mock(Foo.class);
    //Be sure the real implementation is 'safe'.
    //If real implementation throws exceptions or depends on specific state of the object then you're in trouble.
    when(mock.someMethod()).thenCallRealMethod();

As usual you are going to read the partial mock warning: Object oriented programming is more less tackling complexity by dividing the complexity into separate, specific, SRPy objects. How does partial mock fit into this paradigm? Well, it just doesn't... Partial mock usually means that the complexity has been moved to a different method on the same object. In most cases, this is not the way you want to design your application.
However, there are rare cases when partial mocks come handy: dealing with code you cannot change easily (3rd party interfaces, interim refactoring of legacy code etc.) However, I wouldn't use partial mocks for new, test-driven and well-designed code.

### 19. Aliases for behavior driven development (Since 1.8.0) Link icon
Behavior Driven Development style of writing tests uses //given //when //then comments as fundamental parts of your test methods. This is exactly how we write our tests and we warmly encourage you to do so!
Start learning about BDD here: https://en.wikipedia.org/wiki/Behavior-driven_development
The problem is that current stubbing api with canonical role of when word does not integrate nicely with //given //when //then comments. It's because stubbing belongs to given component of the test and not to the when component of the test. Hence BDDMockito class introduces an alias so that you stub method calls with BDDMockito.given(Object) method. Now it really nicely integrates with the given component of a BDD style test!
Here is how the test might look like:
```
 import static org.mockito.BDDMockito.*;

 Seller seller = mock(Seller.class);
 Shop shop = new Shop(seller);

 public void shouldBuyBread() throws Exception {
   //given
   given(seller.askForBread()).willReturn(new Bread());

   //when
   Goods goods = shop.buyBread();

   //then
   assertThat(goods, containBread());
 
```

### 30. Spying or mocking abstract classes (Since 1.10.12, further enhanced in 2.7.13 and 2.7.14) Link icon


### 35. Custom verification failure message (Since 2.1.0) Link icon
Allows specifying a custom message to be printed if verification fails.

Examples:



// will print a custom message on verification failure
verify(mock, description("This will print on failure")).someMethod();

// will work with any verification mode
verify(mock, times(2).description("someMethod should be called twice")).someMethod();
 
