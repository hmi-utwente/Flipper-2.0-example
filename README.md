# Flipper-2.0-example

This example project demonstrates how Flipper can interact with other libraries with a simple bot. In this case it will use Sphinx4 and MaryTTS. This example-bot will respond to either of the three things said: 'hello', 'goodbye' or other text.

To see how the interaction works, check out specifically the `example.xml` template file (../resources/flipper/templates/example.xml). `Sphinx4.java` and `MaryTTS.java` are helper classes to connect to the API of each of these libraries.

## Requirements
* Apache Maven
* Java 8

## Setting up
After downloading or cloning this repository, you can run `mvn install` to install Flipper2.0. After that, run `mvn compile`.

## Running
You can run the example by running `mvn exec:java` in the root folder of the project.
