/*

Maven :- is a build automation tool which helps in creating api and manages dependency
 ./mvnw package

 IOC container it externalize the creation of objects, it is a kind of box which contains all the objects of the classes in it, which can be used when required
 Application context is a way to implement IOC container

 what is rest api? Representational state transfer application programming interfaces
 it is the way of communication between the device and the server
 it helps in accessing something going on the server

 rest api = http verb + url
 http verbs are of 4 types *GET(if we want to see) *PUT(modify) *POST(if we want to create) *DELETE(to delete)
 @RestController



 ORM (object relational mapping) is a technique used to map Java objects to databases tables
    it allows developers to work with databases using object-oriented programming concepts, making it easier to interact with relational databases
    consider a Java class User and database table users.
    ORM framework like hibernate can map the fields in the User class to columns in the user table making it easier to insert,update,retrieve and delete records

   JPA (Java Persistence API) is a way to achieve ORM, includes interfaces and annotations that you use in your JAVA classes, requires a persistence provider (ORM tools) for implementation
   these are the set of rules that implements orm tools

   ORM Tools/ Persistence Provider to use JPA, you need a persistence provider. A persistence provider is a specific implementation of JPA specification
    Eg of JPA persistence provider include HIBERNATE, EclipseLink snd OpenJPA. This provides implement the JPA interfaces and provides the underlying functionality to interact with database

   Spring Data JPA is built on top of the JPA specification, but it is not a JPA implementation itself. Instead, it simplifies working with JPA by providing higher level abstraction and utilities.
   However, to use Spring DataJPA effectively. you still need a JPA implementation, such as Hibernate, EclipseLink, or another JPA Compliant provider to handle the actual database interaction

   JPA is primarily designed for working with relational databases, where data is stored in tables with a predefined schema.
   MongoDB on the other hand is a NoSQL database that uses a different data model,typically based on collections of documents, which are schema less or have flexible schemas, this fundamental difference in data models and storage structure is why JPA is not used with MongoDB
   in the case of MongoDB you don't have to use traditional JPA persistence provider. MongoDB is a NoSQL database, and Spring Data MongoDB serves as the persistence provider for mongoDB
   It provides the necessary abstractions and implementation to work with mongoDB in a spring application

   Integrating MongoDB
              in application properties we will add mongoDB's name,databases,host, password, username, etc

   BEST PRACTICE
   Controller will call service, service will call repository controller --> service --> repository
   Controller end-point banayega and then service ko call karega, service m business logic hoga, service call karega repository ko

   http status code (postman mai jo 200 OK(green)/ 404 BAD(red) se show hota h) the code used to show what process is performed at the backend)
      It is a three-digit numeric code returned by a web server as part of the response to an HTTp request made by client
      These codes are used to convey information about the result or status of the requested operation
      these are grouped into five categories
           i) 1xx (Informational) these status codes indicates that the request was received and understood, and the server is continuing to process it. These are typically used for informational purpose and rarely seen in practice
           ii) 2xx (successful) these codes indicates that the request was successfully received, understood and processed by the server
               200 OK : the request has been successfully processed and the server is returning the request resources
               201 Created : the request has been fulfilled and a new resource has been created as a result
               204 No Content: the request was successful but there is no response body(typically used for operation that don't return data, like successful deletion
           iii) 3xx (redirection) these codes indicates that further action is needed to complete the request. They are used when the client needs to take additional steps to access the requested resource
                301 Moved Permanently: the request resource has been permanently moved to a different URL
                302 Found: it indicates that the requested resource has been temporarily moved to different URL. when a server sends a response with a 302 status code, it typically includes a location header file that specifies the new temporary URL where the client should redirect to
                304 Not Modified: the clients version of the requested resource is still valid, so the server sends this status code to indicate the client can use its cached copy
           iv) 4xx (Client Error) these status code indicate that there was an error on the clients part such as a malformed requested or authentication issue
               400 Bad Request: the server cannot understand or process the clients request due to invalid syntax or other client-side issue
               401 Unauthorized: the client needs to provide authentication credentials to access the requested resource
               403 Forbidden: The client is authenticated, but it does not have permission to access the requested resource
           v) 5xx (Server Error) these status code indicate that there was an error on the server part while trying to fulfill the request
              500 Internal server error: a generic error message indicating that something went wrong on the server and the server could not handle the request
              502 Bad Gateway: the server acting as a gateway or proxy received an invalid response from an upstream server
              503 Service unavailable: the server is currently unable to handle the request due to temporary overloading or maintenance

   Response Entity : the ResponseEntity class is a prt of the spring framework and is commonly used in spring-boot application to customize the HTTP response
                     it provides methods for setting the response status, header and body. You can use it to return different types of data in your controller methods, such as JSON, XML, or even HTML
                     you can use generics with ResponseEntity to specify the type of data you are returning

   LOMBOK (@Data, @Getter, @Setter)
        Lombok is a popular library in the Java ecosystem, often used in spring boot applications
        it aim to reduce the boilerplate code that developers have to write, such as getter, setters, constructors(no args and all args) and more
        Lombok achieves this by generating this code automatically during compilation, based on annotation you add to your java classes
        it generates the bytecode for methods like getters, setters, constructors, equals(), hashcode() and toString() as specified by annotation used in the code. This generated code is added to the compiled class file(.class files)
        when you run the application, the generated methods are available for use, just like any other methods in the classes

   Transaction here means if any of the process fails the whole call will be failed, and the process that are executed have to be rolled back

   The annotation EnableTransactionManagement is used in main class, it says that find all the classes which is annotated with "Transactional" then springboot will make transactional context(container which will have all the db operations in that methods)
   PlatformTransactionManager (it is an interface whose implementation is MongoTransactionManager to handle the transaction ), MongoTransactionManager
   MongoDatabaseFactory helps in building connection with database
   dbFactory is a instance of that

   Spring Security is a powerful and highly customizable security framework that id often used in spring boot applications to handle authentications and authorization
          Authentication( kisko allow knra h and kisko nahi) The process of verifying a user's identity like username and password,
          Authorization(agar access hai to kya kya kr skte h) The process of granting and denying access to specific resources or actions based on the authenticated user's roles and permissions
   once the dependency is added, spring boot auto-configuration feature will automatically apply security to the application
   be default, spring security uses HTTP basic authentication
          HTTP basic authentication:- the client sends an Authorization header "Authorization: Basic<encoded-string>", the server decodes the string, extracts the username and password,and verifies them.
          if they're correct, access is granted. Otherwise an "Unauthorized" response is sent back. By default, all endpoints will be secured
          spring security will generate a default user with a random password that is printed in console logs on startup,  which can also configure username & password in your application.properties



   @Configuration
   @EnableWebSecurity

   public class SpringSecurity extends WebSecurityConfiguration {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                      .antMatchers("/hello").permitAll()
                      .anyRequests().authenticated()
                .and()
                .formLogin();
        }

    }
     @EnableWebSecurity :- this annotation signals Spring to enable its web security support. This is what makes application secure.it is used in conjunction with @Configuration
   WebSecurityConfigurerAdatpter is a utility class in the spring security framework that provides default configuration and allows customization of certain features. By extending it, you can configure and customize spring security for your application needs
    configure: this method provides a way to configure how requests are secured. It defines how request matching should be done and what security actions should be applied
    http.authorizeRequests(): this tells spring security to start authorizing the requests
    .antMatchers("/hello").permitAll(): This part specifies that HTTP requests matching the path /hello should be permitted(allow) for all users, whether they are authenticated or not
    .any: this is a more general matcher that specifies any request (not already matched nu previous matchers) should be authenticated, meaning users have to provide valid credentials to access these endpoints
    .and(): This is a method to join several configuration. it helps to continue the configuration from the root(HttpSecurity)
    .formLogin(): this enables form based authentication. by default it will provide a form for the user to enter their username and password. if the user is not authenticated and they try to access a secured endpoint, they'll be redirected to default login form
    "." this dot is known as method chaining, is used to act with multiple methods
    you can access /hello without any authentication, however if you try to access another endpoints you will be redirected to login form
    when use .formLogin() method in our security configuration without specifying .loginPage("/custom-path"), the default login page becomes active
    spring security provides an in-built controller that handles the /login path. this controller login form when a GET request is made to /login
    by default, spring security also provides logout functionality. When .logout() is configured, a POST request to /logout will log the user out and invalidate their session

    Basic Authentication, by its design, is stateless
    Some applications do mix Basic Authentication with session management for various reasons. This isn't standard behavior and requires additional setup and logic.
    In such scenarios, once the user's credentials are verified via Basic Authentication, a session might be established, and the client is provided a session cookie.
    This way, the client won't need to send the Authorization header with every request, and the server can rely on the session cookie to identify the authenticated user.

    When you log in with Spring Security, it manages your authentication across multiple requests, despite HTTP being stateless.
        1. Session Creation: After successful authentication, an HTTP session is formed. Your authentication details are stored in this session.
        2. Session Cookie: A JSESSIONID cookie is sent to your browser, which gets sent back with subsequent requests, helping the server recognize your session.
        3. SecurityContext: Using the JSESSIONID, Spring Security fetches your authentication details for each request.
        4.Session Timeout: Sessions have a limited life. If you're inactive past this limit, you're logged out.
        5. Logout: When logging out, your session ends, and the related cookie is removed.
        6. Remember-Me: Spring Security can remember you even after the session ends using a different persistent cookie (typically have a longer lifespan).
     In essence, Spring Security leverages sessions and cookies, mainly JSESSIONID, to ensure you remain authenticated across requests.

    we want our springBoot application to authenticate users based on their credentials stored in MongoDB database
    this means users and their passwords(hashed) will be stored in MongoDB, and when a user tries to log in, the system should check the provided credentials against what's stored in the database

    *enabling user authentication
    1. A User entity to represent the user data model.
    @Document(colletion = "user")
    public class User{
    @Id
    private String id;
    private String username;
    private String password;
    private List<String> roles;
    getter and setters
    }

    2. A repository UserRepository to interact with MongoDB.
    public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByuserName(String username);
}
    3. UserDetailsService implementation to fetch user details.


    4. A configuration SecurityConfig to integrate everything with Spring Security.

    csrf: in general springboot makes it enable so that the cookie that are stored cannot be used by other
         for example, if a bank transcation website is open, if csrf is disabled then attacker can use the same api by using cookies
    some application do mix basic authentication with session management for various reasons. This isn't standard behaviour and requires additional setup and logic.
    In such scenarios, once the user's credentials are verified via Basic authentication, a session might be established, and the client is provided a session cookie.
    This way, the client wont need to send the Authorization header with every request, and the servier can rely on the session cookie to identify the authenticated user

    Unit testing :- testing individual components(functions)
    Test driven development : development k sath sath testing
    JUnit is used for test in Java
    test can be run via ./mvnw test too
    jis bhi method to test krna h, usko @Test se annotate kr denge

    Mockito is used for mocking ie in many case we have used userRepo due to which everytime when we have to run test, we need to run the whole application
    so we create a duplicate repo for the test

    ./mvnw clean package -Dspring.profiles.active=dev for making jar with dev yml or prod vice-versa

    LOGGING
      SpringBoot supports various logging frameworkds such as LogBack, Log4j2, and Java Util logging(JUL)
      	Logback: A popular logging framework that serves as the default in many Spring Boot applications. It offers a flexible configuration and good performance.
		Log4j2: Another widely used logging framework with features such as asynchronous logging and support for various output formats.
		Java Util Logging (JUL): The default logging framework included in the Java Standard Edition. While it's less feature-rich than some third-party frameworks, it is straightforward and is part of the Java platform.

		Spring Boot comes with a default logging configuration that uses Logback as the default logging implementation. It provides a good balance between simplicity and flexibility
		The default configuration is embedded within the Spring Boot libraries, and it may not be visible in your project's source code.
		If you want to customize the logging configuration, you can create your own logback-spring.xml or logback.xml file in the src/main/resources directory.
		When Spring Boot detects this file in your project, it will use it instead of the default configuration.
	Logging levels helps in categorizing logs statements based on severity some of common logging levels are
	    TRACE, DEBUG(for debugging), INFO, WARN(there is a warning), ERROR(there is an error )

	    Spring Boot provides annotations like @Slf4j &@Log4j2 that you can use to automatically inject logger instances into your classes
	    can be used with yml too
	    logging:
	    level:
	    com:
	    dhinithya:
	    journalApp:DEBUG

		logging:
		  level:
		    root:  ERROR   to set whole error off

	EXTERNAL API INTEGRATION
	   using rest templates to hit apis

    REDIS it is mainly used for inmemory cache(main memory) earlier used JVM's cache but it need jvm to run first indirectly means to run the SB application
    to configure this, use dependency then add yml/prop
    it uses serialization and deserialization thats why when we fetch the data which is set at redis cli will not be found in the application
    for that make sure the serializer and deserializer of redis and SB are same

 */

package com.dhinithya.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
