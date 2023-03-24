# Weatheripe API 
## Introduction
Sometimes we may have difficulties to decide what to cook for the meal. 
So we introduced Weatheripe which provide a list of Rest API for users to query the recipe based on the weather at their locations.
User can simply input the location. Then system will base on the location to check the weather and returns suitable recipe. 
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
```
mvn compile exec:java -Dexec.mainClass="com.techreturners.weatheripe.WeathipeApplication"
```

## Assumptions:

## Approaches:

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


