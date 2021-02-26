Coverage: 80%
# Project Title

IMS-Starter is an Inventory Management System with java buisness code connecting a console front end to a MySQL backend. The system deals with Items, Customers and their Orders. A four table database is used, with the tables: (customers, items ,orders and orderlines). CRUD functionality is available for Customers, Items and Orders. Orders also has extra features which are "ADDING/DELETING" items abnd "calculatingCost" of an Order. 
Extra information can be found in the "documentation" folder which houses design graphs and a presentation file.

## Getting Started

To run the program you need to clone it to your local machine. Then navigate to the directory "IMS-Starter\target" and open up a terminal inside that directory. Make sure you have Java and MySQL installed (see Prerequisites). To run the program type "java -jar ims-0.0.1-jar-with-dependencies" inside the console.

If you wish to do further devlopment and testing you will need to compile the build the project using maven once changes have been made. This can be done by navigating to "IMS-Starter" opening the console and running "mvn clean package". Your new built program will now showup in the "IMS-Starter\target" for you to run as previously described. 
For more indepth detail see "Installing"

### Prerequisites

Versions listed are the versions known to work, other versions of these prerequisites might work but are not guaranteeded to do so.

Java 1.8-1.14
MySQL 8.0 

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them

```
Give an example
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Vicente Conte Couto** [vicentecontecouto](https://github.com/ecoutoo)
* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Thanks to "Hotel Pools & oDDling - Parrallel" for being a nice track to code along to.
