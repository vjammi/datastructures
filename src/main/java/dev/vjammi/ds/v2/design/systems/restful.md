

### REST Architectural Guiding Principles / Constraints
REST defines 6 architectural constraints which make any web service a truly RESTful API.
    Uniform interface
    Client–server
    Stateless
    Cacheable
    Layered system
    Code on demand (optional)

### Naming REST resources
#### Singleton and Collection Resources
- customers: collection resource  [/customers]
- customer: singleton resource    [/customers/{customerId}]

#### Collection and Sub-collection Resources
- A resource may contain sub-collection resources. For example accounts of a particular customer
       /customers/{customerId}/accounts
- A singleton resource account inside the sub-collection resource accounts
       /customers/{customerId}/accounts/{accountId}

#### URI
REST APIs use Uniform Resource Identifiers (URIs) to address resources. 

#### Naming Best Practices
2.1. Use nouns to represent resources
    RESTful URI should refer to a resource that is a thing (noun) instead of referring to an action (verb) because nouns have properties that verbs do not have – similarly, resources have attributes. Some examples of a resource are:
        Users of the system
        User Accounts
        Network Devices etc.
    
    REST resource URIs can be designed as
    We can divide the resource archetypes into four categories (document, collection, store, and controller)
        
        Document
        A document resource is a singular concept that is akin to an object instance or database record.
            user-management/users/{id}
            device-management/managed-devices/{device-id}

        Collection
        A collection resource is a server-managed directory of resources.
        Clients may propose new resources to be added to a collection. However, it is up to the collection resource to choose to create a new resource or not.
            user-management/users
            user-management/users/{id}/accounts
        
        Store
        A store is a client-managed resource repository. A store resource lets an API client put resources in, get them back out, and decide when to delete them.
            song-management/users/{id}/playlists

        Controller
            cart-management/users/{id}/cart/checkout 
            song-management/users/{id}/playlist/play
2.2. Consistency is the key
1. Use forward slash (/) to indicate hierarchical relationships
   device-management
   device-management/managed-devices
   device-management/managed-devices/{id}
   device-management/managed-devices/{id}/scripts
   device-management/managed-devices/{id}/scripts/{id}

2. Do not use trailing forward slash (/) in URIs

3. Use hyphens (-) to improve the readability of URIs
      devicemanagement/manageddevices/
      device-management/managed-devices 	/*This is much better version*/

4. Do not use underscores (_)

5. Use lowercase letters in URIs

6. Do not use file extensions
      device-management/managed-devices.xml  /*Do not use it*/
      device-management/managed-devices 	/*This is correct URI*/

7. Never use CRUD function names in URIs
   HTTP GET     device-management/managed-devices                // Get all devices
   HTTP POST    device-management/managed-devices                // Create new Device
   HTTP GET     device-management/managed-devices/{id}           // Get device for given Id
   HTTP PUT     device-management/managed-devices/{id}           // Update device for given Id
   HTTP DELETE  device-management/managed-devices/{id}           // Delete device for given Id

8. Use query component to filter URI collection
   Often, you will encounter requirements where you will need a collection of resources sorted, filtered, or limited 
   based on some specific resource attribute. For this requirement, do not create new APIs. Instead, enable 
   sorting, filtering, and pagination capabilities in resource collection API and pass the input parameters as query parameters. e.g.

    device-management/managed-devices
    device-management/managed-devices?region=USA
    device-management/managed-devices?region=USA&brand=XYZ
    device-management/managed-devices?region=USA&brand=XYZ&sort=installation-date


### Resources/Objects, Collections, Sub-Collections, and Endpoints
A resource is an object with a type, associated data, relationships to other resources, and a set of methods that operate on it. 
It is similar to an object instance in an object-oriented programming language, with the important difference that only a few 
standard methods are defined for the resource (corresponding to the standard HTTP GET, POST, PUT and DELETE methods), 
while an object instance typically has many methods.

Resources can be grouped into collections. Each collection is homogeneous so that it contains only one type of resource, and unordered. 
Resources can also exist outside any collection. In this case, we refer to these resources as singleton resources. 
Collections are themselves resources as well.

Collections can exist globally, at the top level of an API, but can also be contained inside a single resource. 
In the latter case, we refer to these collections as sub-collections. 
Sub-collections are usually used to express some kind of “contained in” relationship.

        Collection                     
        ---------
        Resource1               
        Resource2
        Resource3
        Resource4
    [Resources can be grouped into collections]

    A Singleton Resource
    --------------------
        Resource

    Sub Collections and SubResources
    --------------------------------
    Resource
        SubCollection of Resources

    URI 	                        Description
    ----------------------------------------------------------------------------
    /api 	                        The API entry point
    /api/:coll 	                    A top-level collection named “coll”
    /api/:coll/:id 	                The resource “id” inside collection “coll”
    /api/:coll/:id/:subcoll 	    Sub-collection “subcoll” under resource “id”
    /api/:coll/:id/:subcoll/:subid 	The resource “subid” inside “subcoll”


## Stripe Customer API
### Create a Customer
    Request
        POST    /v1/customers
    Response
        {
            "id": "cus_4QFOF3xrvBT2nU",
            "object": "customer",
            "address": null,
            "balance": 0,
            "created": 1405641986,
             ...
        }
### Retrieve a customer [single object]
Retrieves a Customer object
    Request
        GET /v1/customers/:id
    Response
        {
            "id": "cus_4QFOF3xrvBT2nU",
            "object": "customer",
            "address": null,
            "balance": 0,
            "created": 1405641986,
            ...
        }

### Update a customer
Updates the specified customer by setting the values of the parameters passed. Note: This request accepts mostly the same arguments as the customer creation call.
    Request
        POST    /v1/customers
    Response
        {
            "id": "cus_4QFOF3xrvBT2nU",
            "object": "customer",
            "address": null,
            "balance": 0,
            "created": 1405641986,
            ...
        }
### Delete a customer
Permanently deletes a customer. It cannot be undone. Also immediately cancels any active subscriptions on the customer.
    Request
        DELETE /v1/customers/:id
    Response
        {
            "id": "cus_4QFOF3xrvBT2nU",
            "object": "customer",
            "deleted": true
        }
Parameters
    No parameters.
Returns
    Returns an object with a deleted parameter on success. If the customer ID does not exist, this call throws an error.

### List all customers
Returns a list of your customers. The customers are returned sorted by creation date, with the most recent customers appearing first.
    Request
        GET    /v1/customers
    Response
        {
            "object": "list",
            "url": "/v1/customers",
            "has_more": false,
            "data": [
                {
                    "id": "cus_4QFOF3xrvBT2nU",
                    "object": "customer",
                    "address": null,
                    "balance": 0,
                    "created": 1405641986,
                }.
                {...},
                {...}
            ]
        }
Parameters
    email optional
    A case-sensitive filter on the list based on the customer’s email field. The value must be a string.
Returns
    A Map with a data property that contains an array of up to limit customers, starting after customer starting_after. 
    Passing an optional email will result in filtering to customers with only that exact email address. 
    Each entry in the array is a separate customer object. If no more customers are available, the resulting array will be empty. 
    This request should never throw an error.

### Search customers
Search for customers. 
    Does not support search in read-after-write flows where strict consistency is necessary. 
    Under normal operating conditions, data is searchable in less than a minute. 
    Occasionally, propagation of new or updated data can be up to an hour behind during outages.
    Request
        GET /v1/customers/search
            .setQuery("name:'fakename' AND metadata['foo']:'bar'")
    Response
        {
            "object": "search_result",
            "url": "/v1/customers/search",
            "has_more": false,
            "data": [
                {
                    "id": "cus_4QFOF3xrvBT2nU",
                    "object": "customer",
                    "address": null,
                    "balance": 0,
                    "created": 1405641986,
                }.
                {...},
                {...}
            ]
        }

Parameters
    query REQUIRED
    The search query string. See search query language and the list of supported query fields for customers.
    limit optional
    A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 10.
    page optional
    A cursor for pagination across multiple pages of results. Don’t include this parameter on the first call. Use the next_page value returned in a previous response to request subsequent results.

Returns
    A dictionary with a data property that contains an array of up to limit customers.
    If no objects match the query, the resulting array will be empty.

###JSON Types 
In JSON, three types of data exist
    Scalar (number, string, boolean, null)
        Scalar types have just a single value.
    Array
        Arrays contain an ordered list of values [similar/arbitrary type???]
    Object
        Objects consist of an unordered set of key:value pairs

```
    {
      "type": "vm",
      "name": "A virtual machine",
      "memory": 1024,
      "cpu": {
        "cores": 4,
        "speed": 3600
      },
      "boot": {
        "devices": ["flash", "ssd"]
      }
    }
```
### Content-Types
    “application/x-resource+format” 
    “application/x-collection+format”

### Selecting a Representation Format
Clients can express their preference for a certain representation format using the HTTP “Accept” header.
    GET /api/collection
    Accept: application/x-collection+yaml


### HTTP POST Vs HTTP PUT and Idempotency
Based on HTTP 1.1 specification 
- A GET, HEAD, DELETE, and PUT methods must be idempotent 
- A POST method is not idempotent 
Now, what does it mean for an operation to be idempotent? 
- An operation though performed multiple times on a resource, the state of the resource must remain the same across the updates - as if it was performed only once.
- A non-idempotent operation can modify the state of a resource across the updates/requests, leaving no guarantees of maintaining the state of the resource across updates.


Reference: https://stackoverflow.com/questions/630453/what-is-the-difference-between-post-and-put-in-http
Answer # 1
Overall
    Both PUT and POST can be used for creating.
    You have to ask, "what are you performing the action upon?", to distinguish what you should be using. Let's assume you're designing an API for asking questions. If you want to use POST, then you would do that to a list of questions. If you want to use PUT, then you would do that to a particular question.
    Great, both can be used, so which one should I use in my RESTful design:
    You do not need to support both PUT and POST.
    Which you use is up to you. But just remember to use the right one depending on what object you are referencing in the request.

Some considerations
    Do you name the URL objects you create explicitly, or let the server decide? If you name them then use PUT. If you let the server decide then use POST.
    PUT is defined to assume idempotency, so if you PUT an object twice, it should have no additional effect. This is a nice property, so I would use PUT when possible. Just make sure that the PUT-idempotency actually is implemented correctly in the server.
    You can update or create a resource with PUT with the same object URL
    With POST you can have 2 requests coming in at the same time making modifications to a URL, and they may update different parts of the object.

An example:
I wrote the following as part of another answer on SO regarding this:

POST:
Used to modify and update a resource
        POST /questions/<existing_question> HTTP/1.1
        Host: www.example.com/

    Note that the following is an error:
        POST /questions/<new_question> HTTP/1.1
        Host: www.example.com/
    
    If the URL is not yet created, you should not be using POST to create it while specifying the name. This should result in a 'resource not found' error because <new_question> does not exist yet. You should PUT the <new_question> resource on the server first.
    
    You could though do something like this to create a resources using POST:
        POST /questions HTTP/1.1
        Host: www.example.com/
    Note that in this case the resource name is not specified, the new objects URL path would be returned to you.

PUT:
Used to create a resource, or overwrite it. While you specify the resources new URL.

For a new resource:
    PUT /questions/<new_question> HTTP/1.1
    Host: www.example.com/
To overwrite an existing resource:
    PUT /questions/<existing_question> HTTP/1.1
    Host: www.example.com/

Additionally, and a bit more concisely, RFC 7231 Section 4.3.4 PUT states (emphasis added),

4.3.4. PUT
The PUT method requests that the state of the target resource be created or replaced with the state defined by the representation enclosed in the request message payload.

Answer # 2 
There seems to always be some confusion as to when to use the HTTP POST versus the HTTP PUT method for REST services. Most developers will try to associate CRUD operations directly to HTTP methods. I will argue that this is not correct and one can not simply associate the CRUD concepts to the HTTP methods. That is:
    Create => HTTP PUT
    Retrieve => HTTP GET
    Update => HTTP POST
    Delete => HTTP DELETE
It is true that the R(etrieve) and D(elete) of the CRUD operations can be mapped directly to the HTTP methods GET and DELETE respectively. However, the confusion lies in the C(reate) and U(update) operations. In some cases, one can use the PUT for a create while in other cases a POST will be required. The ambiguity lies in the definition of an HTTP PUT method versus an HTTP POST method.

According to the HTTP 1.1 specifications the GET, HEAD, DELETE, and PUT methods must be idempotent, and the POST method is not idempotent. That is to say that an operation is idempotent if it can be performed on a resource once or many times and always return the same state of that resource. Whereas a non idempotent operation can return a modified state of the resource from one request to another. Hence, in a non idempotent operation, there is no guarantee that one will receive the same state of a resource.

Based on the above idempotent definition, my take on using the HTTP PUT method versus using the HTTP POST method for REST services is: Use the HTTP PUT method when:
The client includes all aspect of the resource including the unique identifier to uniquely identify the resource. Example: creating a new employee.
The client provides all the information for a resource to be able to modify that resource.This implies that the server side does not update any aspect of the resource (such as an update date).

In both cases, these operations can be performed multiple times with the same results. That is the resource will not be changed by requesting the operation more than once. Hence, a true idempotent operation. Use the HTTP POST method when:

The server will provide some information concerning the newly created resource. For example, take a logging system. A new entry in the log will most likely have a numbering scheme which is determined on the server side. Upon creating a new log entry, the new sequence number will be determined by the server and not by the client.

On a modification of a resource, the server will provide such information as a resource state or an update date. Again in this case not all information was provided by the client and the resource will be changing from one modification request to the next. Hence a non idempotent operation.

Conclusion: Do not directly correlate and map CRUD operations to HTTP methods for REST services. The use of an HTTP PUT method versus an HTTP POST method should be based on the idempotent aspect of that operation. That is, if the operation is idempotent, then use the HTTP PUT method. If the operation is non idempotent, then use the HTTP POST method.

Answer #3
- PUT is like an upsert (insert or update)
- POST is an insert.
  When you PUT twice we get the one new record, however when we POST twice, we get two new records
- 
## Spring Rest

### @GetMapping
The client sends an HTTP request to the REST service. The dispatcher servlet handles the request and if the request has
JSON data, the HttpMessageConverter converts it to Java objects. The request is mapped to a controller which calls
service layer methods. The service layer delegates the call to repository and returns the data as POJO. 
The MessageConverter converts the data to JSON and it is sent back to the client. 
              REST API         Application Layer        Service Layer       Repository              DB
            -------------------------------------------------------------------------------------------
            Dispatcher      
             Servlet             Controller               Hibernate          JpaRepository                               
Client
              HTTP 
        MessageConverter
        [JSON <--> POJO]


