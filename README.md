# Mortage Plan code test

The application creates Customers from a text file and calculates the customers' monthly mortage cost.
Written entirely in Java

## Future plans

* Gradle
* Testing
* Docker
* Web interface

## Installation
Currently Gradle is being implemented.
To run the application do the following

```bash
javac app/main/java/bank/App
java app/main/java/bank/App
```

## Why Gradle?
I chose Gradle over Maven mainly for their incremental build which makes rebuilding the application way faster as only changed files have to be rebuilt.
Gradle also uses codebased build scripts instead of XML-files which is easier.
Overall Gradle outperforms Maven.