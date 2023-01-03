# Testing Java with JUnit 5 & Mockito 

These are personal notes on the Udemy course "Testing Java with JUnit 5 & Mockito" by Sergey Kargopolov.

## Section 1: Introduction

This part is more theoretical.
Here the course first explains what a unit test is in general.
It is mentioned that unit tests are very fast because mocks/fakes/spies are mostly used when dependencies exist.
The following reasons are given why we should write unit tests:
* Make sure the code works
* Code works with valid and invalid input parameters
* Code works now and in the future
* Other code still works even after you made changes (Regression Testing)

It also explains what *Testing Code in Isolation* means and why it is important for unit testing.
In this context, the importance of *Dependency Injection* is discussed.

The Testing Pyramid is discussed, consisting of:
1) Unit tests (bottom, runs before any other tests, testing isolated small pieces of code with fake/mock dependencies)
2) Integration tests (use real dependencies/objects, Application code is tested without mocking db or http connections)
3) End-to-End Testing / UI Testing (top, automated tests, Testing functionality from beginning to end e. g. with selenium)


But what is JUnit 5?  

A testing framework for unit testing in Java.

JUnit5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
* JUnit Platform: Serves as a foundation for launching testing frameworks on the JVM.
* JUnit Jupiter: A combination of new programming model + extension model for writing tests and extensions in JUnit5 (annotations/assertions, extension api allows us to create our own version of test api)
* JUnit Vintage: Test engine for older versions of JUnit.

## Section 2: Add JUnit5 to Maven project

The course explains what to do if you want to use JUnit5 in a project with Maven.
For this purpose, a Maven project *CalculatorMavenProject* is created.
JUnit Jupiter (aggregator) is required in the pom.xml:

```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.9.1</version>
    <scope>test</scope>
</dependency>
```

In order to be able to run the unit tests in the Maven build lifecycle, we need to add the Surefire plugin:
```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M7</version>
        </plugin>
    </plugins>
</build>
```

## Section 5: Basics of JUnit 5

You can use messages in an assertion, which are called when the test fails. 
You can also do this lazy by using a lambda expression:
```
assertEquals( expectedResult, result,
            () -> minuend + "-" + subtrahend + " did not produce " + expectedResult
        );
```

Good about this section is that the structure of a unit test is explained. 
The scheme arrange/act/assert is used here. However, I prefer to use given/when/then
like Martin Fowler.

### JUnit Test Lifecycle

By default, an instance of the test class is created for each test method when running JUnit tests.
The order in which a test is carried out is "random".
There are a few lifecycle annotations:
* @BeforeAll (Annotated method is called before any test method is called. For example to create a DB.)
* @AfterAll (Clean Up Method. E. g. to delete DB)
* @BeforeEach (Executed before each test method)
* @AfterEach (Clean Up Method)

![img_2.png](img_2.png)

If you want to test for exceptions, you can use assertThrows:

```
ArithmeticException actualException = assertThrows(ArithmeticException.class, () ->
        {
            // When
            calculator.integerDivision(dividend, divsior);
        }, "Division by zero should throw an ArithmeticException");

        // Then
        assertEquals(expectedExceptionMessage, actualException.getMessage(),
                "Unexpected exception message");
```