package io.dev.v2.z.rmi;

/*
    Step 1: Defining the remote interface
    The first thing to do is to create an interface that will provide the description of the methods that can be invoked by remote clients.
    This interface should extend the Remote interface and the method prototype within the interface should throw the RemoteException.
**/
// Creating a Search interface
import java.rmi.*;
public interface SearchService extends Remote{

    // Declaring the method prototype
    String query(String search) throws RemoteException;
}
