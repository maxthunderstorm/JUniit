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





