Coverage: 80%
# Project Title

IMS-Starter is an Inventory Management System with java buisness code connecting a console front end to a MySQL backend. The system deals with Items, Customers and their Orders. A four table database is used, with the tables: (customers, items ,orders and orderlines). CRUD functionality is available for Customers, Items and Orders. Orders also has extra features which are "ADDING/DELETING" items and "calculatingCost" of an Order. 

Extra information can be found in the "documentation" folder which houses design graphs and a presentation file.

## Getting Started

To run the program you need to clone it to your local machine. Then navigate to the directory "IMS-Starter\target" and open up a terminal inside that directory. Make sure you have Java and MySQL installed (see Prerequisites). To run the program type "java -jar ims-0.0.1-jar-with-dependencies" inside the console.

If you wish to do further devlopment and testing you will need to compile the build the project using maven once changes have been made. This can be done by navigating to "IMS-Starter" opening the console and running "mvn clean package". Your new built program will now showup in the "IMS-Starter\target" for you to run as previously described. 

For more indepth detail see "Installing"

### Prerequisites

Versions listed are the versions known to work, other versions of these prerequisites might work but are not guaranteeded to do so.

Java 1.8-1.14 (https://www.java.com/en/download/help/download_options.html)

MySQL 8.0 (https://dev.mysql.com/doc/refman/8.0/en/windows-installation.html)

Java IDE - Eclipse (https://www.eclipse.org/downloads/packages/installer) - Or any other java IDE you prefer

### Installing

Step 1: Clone to local machine

Step 2: browse to "IMS-Starter\target" directory

Step 2: Type "java -jar ims-0.0.1-jar-with-dependencies" to run the program

## Running the tests

The automated tests run when the program is being packaged by maven, if you wish to run them in Eclipse on your package explorer navigate to "ims" then right click "src/test/java" and select coverage as JUnit test. This will run the automated testes inside the IDE and highlight tested/untested code and report back an test successes/errors/failures.

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
