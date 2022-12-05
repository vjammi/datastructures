package dev.vjammi.ds.v2.z.rmi;

// Java program for server application
import java.rmi.*;
import java.rmi.registry.*;

/*
    Step 3: Creating Stub and Skeleton objects from the implementation class using rmic
    The rmic tool is used to invoke the rmi compiler that creates the Stub and Skeleton objects. Its prototype is rmic classname.
    For above program the following command need to be executed at the command prompt rmic SearchQuery.

    Step 4: Start the rmiregistry
    Start the registry service by issuing the following command at the command prompt start rmiregistry

    Step 5: Create and execute the server application program
    The next step is to create the server application program and execute it on a separate command prompt.

    The server program uses createRegistry method of LocateRegistry class to create rmiregistry within the server JVM with
    the port number passed as an argument.
    The rebind method of Naming class is used to bind the remote object to the new name.
*/

public class SearchServer {

    public static void main(String[] args) {
        try {
            int port = 1900;
            // Create an object of the interface implementation class
            SearchService remoteObject = new SearchServiceImplementation();
            System.out.println("Started Search Service. Listening on port " + port);

            // rmiregistry within the server JVM with port number 1900
            LocateRegistry.createRegistry(port);
            System.out.println("Created Registry on port" + port);

            // Binds the remote object by the name api
            Naming.rebind("rmi://localhost:1900/api", remoteObject);
            System.out.println("Binding rmi://localhost:1900/api to teh remote object " + remoteObject);
        } catch (Exception ae) {
            System.out.println(ae);
        }
    }
}


