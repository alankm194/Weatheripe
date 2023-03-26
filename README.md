# Weatheripe API 
## Introduction
Sometimes we may have difficulties to decide what to cook for the meal. 
So we introduced Weatheripe which provide a list of Rest APIs for users to query the recipes based on the weather at their locations.
User can simply input the location. Then Weatheripe will base on the location to check the weather and returns suitable recipes. 
eg. If it is hot, Weatheripe would recommend recipes which can cool you down. If it is cold, Weatheripe would recommend recipes to warm you up.

## Technology
Java 17, Spring Boot 3.0.2

## UML Diagram
1. DB Schema

![DB Schema](Group2-Project-DBSchema.drawio.png)

2. Class Diagram

![Class diagram](Group2-Project-ClassDiagram.drawio.png)

## Key Features

Weatheripe utilize the tomorrow.io APIs to get the weather of the specified location, eg. London and then based on internal data processing to decide which food type are suitable for the weather and pass it to Edamam API to get the recipe result to recommend to our user. 

### Public functions:
1. Recommend recipe by the temperature of the location input
2. View the Dish Type from our DB

### Registered User functions:
User managements:
1. User can register for an user accounts
2. User can also unregistered their user accounts when they don't need the functions

Registered user can enjoy the below features: 
1. User can save the favourite recipe into their user account
2. User can retrieve the recipe lists that saved
3. User can update the rating of the favourite recipes
4. User can remove the recipes from its favourite recipes list

### Use case diagram

![Use case diagram](Group2-Project-UseCase-Simplified.drawio.png)

## How to run unit test
```
mvn clean test
```

## How to run the program

### Setup

To run the application it requires a RSA private key in PKCS #8 format and a RSA public key. 

to generate your own keys you can run into the commands below into your terminal
```
openssl genpkey -algorithm RSA -out rsa.private.key -pkeyopt rsa_keygen_bits:2048

openssl rsa -pubout -in rsa.private.key -out rsa.private.key
```

For testing and development you can store these keys in as files with the same name in 
```
src/main/resources
```

For running in production the RSA keys can be set using environment variables using the below environment variables

```
JWT_PRIVATE_KEY 

JWT_PUBLIC_KEY
```

You must have the following environment variables set

```
SECRETKEY_RECIPEAPPKEY: Edamam Recipe API key
SECRETKEY_RECIPEAPPID: Edamam Recipe App ID
SECRETKEY_WEATHERAPIKEY= Tomorrow.io Weather API key
```

### running

The application can be run using the command below.

```
mvn compile exec:java -Dexec.mainClass="com.techreturners.weatheripe.WeathipeApplication"
```
API documentation can be found in [Swagger](http://localhost:8080/swagger-ui/index.html) after local server start up.

## Assumptions:
- All APIs that this project depends on, including the Tomorrow.io weather API and the Edamam recipe API, are up and running and accessible.
- All API keys used in the project to access the Tomorrow.io and Edamam APIs are valid and authorized to access those APIs.
- All locations passed to the API are mapped to a corresponding temperature value in the target API.
- The location input parameter passed to the API must be either a city, a postal code, or latitude and longitude coordinates in the format "latitude,longitude".
- The temperature values returned by the API are in Celsius.
- All recipe parameters passed to the Edamam API are valid and conform to the expected recipe search criteria.

## Approaches:

This Java Spring Boot API project uses the following approaches:

### Architecture
The project follows the Model-View-Controller (MVC) architecture pattern, which separates the application into three interconnected components: the model, the view, and the controller. This allows for a clean separation of concerns and makes the codebase easier to manage.

### Frameworks and Libraries
The project uses the following frameworks and libraries:

- Spring Boot: version 3.0.2 of this popular framework for building enterprise-level Java applications is used to provide a powerful and flexible foundation for the project.
- Hibernate: A powerful ORM tool for working with databases.
- JPA: A Java Persistence API that provides a standard way to work with relational databases.
- io.jsonwebtoken: version 0.11.5 of this library is used for secure authentication and authorization via JSON Web Tokens (JWTs).
- WebFlux: the reactive web framework for building non-blocking, event-driven applications is used to enable high-concurrency processing of web requests with minimal overhead.
- com.c4-soft.springaddons: version 6.1.3 of this library provides additional features and utilities for Spring Boot applications, such as simplified configuration of beans and templates.
- Spring Docs: a framework that works with spring boot to provide OpenAPI and Swagger for API documentation.

### Database
The project uses Postgres as the deployment database and H2DB for testing and development purposes.

### Testing
The project uses the following testing frameworks and libraries:

- Mockito: A mocking framework for Java that allows for easy testing of dependencies.
- JUnit: A popular unit testing framework for Java that provides a simple and efficient way to test code.

The project uses GitHub Actions to run tests for any pull requests to master. 

### Security
The project uses Spring Security with JWT tokens and user role-based access control to secure the endpoints. This provides a robust and secure way to authenticate and authorize users of the API.

### Documentation
The Swagger documentation for this project can be accessed by navigating to the [Swagger](http://localhost:8080/swagger-ui/index.html) endpoint once the application is running.


## Future thoughts:
Below are the functions to be implemented:
1. Allow user saving dietary preferences and use it to search recipes, so that system can base on the dietary preference to recommended suitable recipes to users.
2. Allow user to subscribe, so user can receive our recommend recipe by time by day with a sms
3. Food diary
4. Recommend recipe by location, based on the location, Weatheripe to recommend user the recipe that belongs to their location, so that user can buy ingredients easily. eg. If you are in UK, Weatheripe will recommend British food, to ensure that most ingredients you can buy are easily available in local supermarket and eliminate the headache to find the ingredients.
5. Deployment to AWS cloud
6. Add front end user interface
7. Add CI/CD pipeline

## Reference:

1. https://www.tomorrow.io/
2. https://www.edamam.com/


