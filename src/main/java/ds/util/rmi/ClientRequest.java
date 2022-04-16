package ds.util.rmi;

// Java program for client application

import java.rmi.*;

/*
    Step 6: Create and execute the client application program
    The last step is to create the client application program and execute it on a separate command prompt .
    The lookup method of the Naming class is used to get the reference of the Stub object.
*/

public class ClientRequest {

    public static void main(String args[]) {
        String response = null;
        String request = null;
        try {
            // lookup method to find reference of remote object
            SearchService service = (SearchService) Naming.lookup("rmi://localhost:1900/api");

            request = "Got Milk";
            response = service.query(request);
            System.out.println("Queried the Remote Search Service with request: " + request);
            System.out.println("Response Received: " + response);

            request = "Got Cheese";
            response = service.query(request);
            System.out.println("Queried the Remote Search Service with request: " + request);
            System.out.println("Response Received: " + response);

        } catch (Exception ae) {
            System.out.println(ae);
        }

    }
}
