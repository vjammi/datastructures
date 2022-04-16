package ds.util.rmi;

// Java program to implement the Search interface

import java.rmi.*;
import java.rmi.server.*;

/*
    Step 2: Implementing the remote interface
    The next step is to implement the remote interface. To implement the remote interface, the class should extend to UnicastRemoteObject class of java.rmi package.
    Also, a default constructor needs to be created to throw the java.rmi.RemoteException from its parent constructor in class.
*/

public class SearchServiceImplementation extends UnicastRemoteObject implements SearchService {

    // Default constructor to throw RemoteException
    // from its parent constructor
    SearchServiceImplementation() throws RemoteException {
        super();
    }

    // Implementation of the query interface
    public String query(String search) throws RemoteException {
        String result;
        if (search.equals("Got Milk"))
            result = "Yes";
        else
            result = "No";
        return result;
    }
}
