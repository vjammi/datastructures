## Mocking

### Argument Captor 
```
1. Setup the argument captor
    ArgumentCaptor<String> customerIdArgumentCaptor = ArgumentCaptor.forClass(String.class);

2. Invoke the subject
        subject.deleteCustomer(customerId)
3. 
4. Verify the call to the dependent on component (collaborator of subject under test). times(1) is the default value, so ne need to add it.
    verify(repository, times(1)).delete(customerIdArgumentCaptor.capture());

5. Get the argument passed to collaborator using getValue() of the captor
     String customerId = messageCaptor.getValue();

5   Assert the actual and captured argument captor
```