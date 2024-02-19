REST API Design

Our REST API will expose endpoints which allow a REST client to perform the following functions:
Get a list of books Get a book by ID Add a new book Update an existing book Update the titles of a book Delete an book

We will make use of the HTTP GET, POST, PUT, PATCH, and DELETE methods to perform these operations. To create service
endpoints for our REST API, we need to identify the entity (main resource - book). A REST API design convention is to
use the plural of the entity as the endpoint, so we will use /books.

    Operation               HTTP Method       URL
    -----------------------------------------------------
    Show all books            GET             /books
    Create an book            POST            /books
    Update a book             PUT             /books
    Partial update a book     PATCH           /books
    Show book by ID           GET             /books/{id}
    Delete a book             DELETE          /books/{id}

    Operation               HTTP Method       URL
    -----------------------------------------------------
    Show all users for a group            GET             /groups/{groupId}/users
    Create an user within a group         POST            /groups/{groupId}/users
    Update a user  within a group         PUT             /groups{groupId}/users
    Partial update a user within a group  PATCH           /groups{groupId}/users
    Get me user from a group              GET             /groups/{grouId}/users/{userId}
    Delete a user from a group            DELETE          /groups/{id}/users/{userId}

#### Required dependencies

spring-boot-starter-web - spring-webmvc - this loads all the supporting dependencies like spring-core, spring-context,
spring-web - jackson-databind dependency for JSON data binding - spring-boot-starter-data-jpa dependency for Hibernate
ORM support - spring-boot-starter-test for test dependencies

### Creating a repository

We will create an interface BookRepository.java which extends the JpaRepository interface and provide the entity and the
data type of the primary key:

```
    import org.springframework.data.jpa.repository.JpaRepository;
    
    public interface ItemRepository extends JpaRepository <Book, Integer> {
    
    }
```

Simply by extending the JpaRepository, we get all basic CRUD operations, without having to write any implementation.

- findAll(),
- findById(),
- save()
- deleteById()

### Service layer

As a best practice, we will introduce a service layer on top of the repository. We will create a class BookService.java
and use the @Service annotation to indicate that this class belongs to the service layer. To use the BookRepository in
the service layer, we will autowire it and then delegate calls to the methods provided by the JpaRepository.

```
@Service
public class BookService {
    
    @Autowired
    private BookRepository repo;

    //method to return all books

    //method to find book by id

    //method to add book

    //...
}
```

### @RestController annotation

This annotation is an extension of @Controller annotation. The @RestController annotation has support for REST requests
and responses and automatically handles the data binding between the Java POJOs and JSON.

```
@RestController
public class BookController{

}
```

### @GetMapping

```
    import java.util.List;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;
    
    @RestController
    public class ItemController {
    
        @Autowired
        ItemService service;
    
        // The @GetMapping annotation maps HTTP GET requests to controller methods. 
        // It is a shortcut for @RequestMapping(method=RequestMethod.GET)        
        @GetMapping("/books")
        public List<Item> allItems() {
            return service.getAllItems();	    
        }
    }
```

### @PathVariable

Path variables are a way of parameterizing the path or endpoint to accept data. Path variables are written in curly
braces. When the client sends a request, it passes a value in place of the path variable. The REST client will send a
request to /books/{bookId}, where bookId is a path variable

```
/books/{bookId}
/books/1001
```

Since there is a path variable in the endpoint, we need to bind it with a method parameter. The @PathVariable annotation
binds the path variable {id} from the URL to the method parameter id. By default, both the names must be the same for
the binding to work.

```
    public Item getItem(@PathVariable int id){
        return service.getItem(id);
    }
```

If the names of the path variable and the method parameter are different, then we need to specify the path variable as
argument of the @PathVariable annotation. In the code snippet below, the path variable id will bind with the variable
ItemId.

```
    public Item getItem(@PathVariable("id") int bookId) {
    
    }
```

### @PostMapping

The @PostMapping annotation maps HTTP POST requests to controller methods. It is a shortcut annotation for
@RequestMapping(method=RequestMethod.POST)

The REST client will send a POST request to / books. The body of the request contains the JSON data for a Item. Since
this is a new Item, the client will not pass the ID (primary key). The backend system will generate the key for the new
record. The REST service will convert JSON data to POJO and add it to the database. The primary key of the added Item is
automatically generated by Hibernate, which is the default ORM used by Spring Data JPA. The response to the client is an
echo of the Item details along with the newly generated ID value.

We will begin by writing the service layer method to add a Item. This method, add Item takes a Item object as parameter
and returns the entity that has been added.

```
public  Item add Item( Item p){
    //call  repository  method to add a  Item object to the  Item table
}
```

The JpaRepository interface inherits a method from the CrudRepository called save. This method handles both inserts and
updates. To distinguish between an INSERT and UPDATE operation, the save method checks the primary key of the object
that is being passed to it. If the primary key is empty or null, an INSERT operation is performed, otherwise an UPDATE
to an existing record is performed.

```
public  Item add Item( Item p){
    return repo.save(p);
}
```

The calling method will ensure that the primary key, Id, in the Item object is empty so that the record gets inserted.

After writing the service layer method for adding a Item, we are ready to move on to the controller layer. We will
create a method add Item in the ItemController class. This method will have a mapping for a POST request to / books. The
method will return the inserted record back to the client.

```
    @PostMapping("/ books")
        public  Item add Item( Item  Item){
    }
```

### @RequestBody

The client will send the Item data in the request body as JSON. Jackson will convert the incoming JSON data to POJO. The
@RequestBody annotation handles this conversion and binds the data in the request body to a method parameter.

```
    @PostMapping("/ books")
    public  Item add Item(@RequestBody  Item  Item){
    
    }
```

The @RequestBody annotation binds the JSON from the request to the Item object. It converts JSON to POJO without us
having to parse the request body. We can directly use the data in the Item object now.

```
POST: 
    localhost:8080/books
body:
    {
        "name": "table",
        "color": "gray"        
    }

```

ItemRepository

```
import org.springframework.data.jpa.repository.JpaRepository;
public interface  ItemRepository extends JpaRepository< Item, Integer>{
	
}

```

bookservice

```
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  BookService {
	
	@Autowired
	 ItemRepository repo;
	
	//Get all  books
	public List< Item> getAll books() {
		return repo.findAll();	    
	}

	//Get  Item by ID
	public  Item get Item(int id) {
		Optional< Item> temp Item = repo.findById(id);

		if(temp Item.isEmpty())
			throw new RuntimeException(" Item with id {"+ id +"} not found");
		
		return temp Item.get();
	}
	
	
	public  Item add Item( Item p) {
		return repo.save(p);
	}
}	
```

REST Controller

```
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  ItemController {

	@Autowired
	 Itemservice service;
	
	@GetMapping("/ books")
	public List< Item> all books() {
		return service.getAll books();	    
	}

	@GetMapping("/ books/{id}")
	public  Item get Item(@PathVariable int id){
		return service.get Item(id);
	}
	
	@PostMapping("/ books")
	public  Item add Item(@RequestBody  Item  Item) {
    		 Item.setId(0);
		return service.add Item( Item);
	}
}
```

### @PutMapping

The @PutMapping is a shortcut annotation for mapping a PUT request to a controller method. It is the same as:
@RequestMapping(method=RequestMethod.PUT)

The HTTP PUT request is used for updates. The REST client will send a PUT request to / books/{ ItemId} with JSON data
containing the information to be updated. The Item’s ID is a path variable.

```
PUT request:
    localhost:8080/books/{bookId}
body:
    {
        "name": "table",
        "color": "gray"        
    }
```

The update Item method accepts JSON data in the request body. The @RequestBody annotation binds the JSON data to the
Item object Item. It handles the conversion from JSON to POJO. We have also used the @PathVariable annotation which will
extract the path variable id from the incoming request /books/{id} and bind it with the id method parameter.

```
@PutMapping("/books/{id}")
public  Item update Item(@RequestBody  Item  Item, @PathVariable int id){
    return service.update Item(id,  Item);
}
```

### @PatchMapping

The PUT method updates the whole record. There may be a scenario when only one or two fields needs to be updated. In
that case, sending the whole record does not make sense. The HTTP PATCH method is used for partial updates.

```
PATCH request:
    localhost:8080/books/{bookId}
body:
    {
        "color": "blue"        
    }
```

The @PatchMapping is a shortcut annotation. It is the same as:
@RequestMapping(method = RequestMethod.PATCH);

```
@PatchMapping("/books/{id}")
public  Item partialUpdate( @PathVariable int id, @RequestBody Map<String, Object>  ItemPatch) {
    return service.patch(id,  ItemPatch);          
}
```

#### Using Queries for partial update

used the save method to applying the patch. This method updates all the columns in the table. For large objects with a
lot of fields, this can have a performance impact. To avoid this, we can implement queries for partial updates. The
@Query annotation is used to implement an update query as follows:

```
@Modifying
@Query("update Item p set p.color = :color where p.id = :id")
void updateColor(@Param("id") int id, @Param("color") int color);
```

The query must be used with the @Modifying annotation to execute the UPDATE query. The @Param annotation binds the
method parameters to the query. This method will only change a single column of the table unlike the save method which
updates all the columns of the table.

```
@Transactional
public void updateTitles(int id, int titles) {
    repo.updateTitles(id, titles);
}
```

The @Transactional annotation ensures that the database is left in a consistent state. The transaction is either
committed or rolled back in case of failure.

```
@PatchMapping("/books/{id}/titles")
public void updateTitles(@PathVariable int id, @RequestBody int titles) {
  service.updateTitles(id, titles);           
}
```
#### ResponseEntity in Spring
https://technicalsand.com/using-responseentity-in-spring/

#### What Is REST API? Examples And How To Use It

https://youtu.be/-mN3VyJuCjM
https://youtu.be/-mN3VyJuCjM

#### HTTP/1 to HTTP/2 to HTTP/3

https://youtu.be/a-sBfyiXysI

### Additional References

https://news.ycombinator.com/item?id=32506784
I would go for the hierarchical urls, like:

- /organizations/:organizationId/blogs/:blogId
- /organizations/:organizationId/blogs/:blogId/sections/:sectionId
- /organizations/:organizationId/blogs/:blogId/threads/:threadId/comments/:commentId I do this because it expresses the
  structure and the constraints of the data. A blog cannot exist without being linked to an organisation. And then you
  typically have the following CRUD endpoints:
- /organizations/:organizationId/blogs, with GET and POST to retrieve a list of blogs belonging to an organization and
  to create a blog for an organization
- /organizations/:organizationId/blogs/:blogId, with GET, PUT and DELETE to retrieve, update and remove a blog The fact
  that you always have the :organizationId in the url likely means that you can easily verify authorization, because I
  assume you would have a link between the user and an organization and maybe that link would already be available in
  the access token. I don't think it is a problem that the urls are heavily nested. The urls are not supposed to be
  manually entered, but are using from within an application. I like the use of HATEOAS links, because I don't really
  like the frontend trying to assemble urls by itself. Also note that next to the hierarchical links, you might need
  some extra for search that are not hierarchical. For example, say that there is a need to search blogs across
  organizations. This could be provided using the following:
- /blogs?description=test&language=en You can provide a search endpoint in the root for blogs. This endpoint could then
  return some kind of blog object with a reference to the actual blog location (like /organizations/34/blogs/11). When
  you use this approach, the frontend has no need to assemble urls and the structure or complexity of the url does not
  matter. Do note that the frontend still needs some generic "start" urls like the blogs search endpoint. Additional
  advantage (like others have mentioned), is that with the hierarchical urls it becomes more difficult to "guess" an
  url. Altough, if you want to properly protect against that, you should transform the ids before putting them in the
  url and transform them back when reading them out of an incoming url, for example using symmetric encryption with a
  secret only known in the backend.

References
https://google.aip.dev/general
https://www.moesif.com/blog/technical/api-design/REST-API-Design-Best-Practices-for-Sub-and-Nested-Resources/
https://dev.tasubo.com/2021/08/quick-practical-introduction-to-restful-apis-and-interfaces.html#resources-should-have-well-defined-locations-uris



#### Additional References
https://technicalsand.com/using-responseentity-in-spring/