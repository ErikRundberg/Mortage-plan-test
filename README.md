# Mortage Plan code test

The application creates Customers from a text file and calculates the customers' monthly mortage cost and presents it.

Written entirely in Java

## Installation
To run the application do the following:
Make sure you have a version of Java installed
Be in root folder

```bash
./gradlew run
```

## Future plans

* Testing
* Docker
* Web interface (Spring Boot)
~~* Gradle~~

## Choices

### Name formatting from file
I decided to keep `"Clarencé,Andersson"` as it was written instead of removing the quotation marks and the comma to show that I can handle them.

I probably would've formatted it into `Clarencé Andersson` to keep it consistent with other entries in prospects.txt.

### Why Gradle?
I chose Gradle over Maven mainly for their incremental build which makes rebuilding the application way faster as only changed files have to be rebuilt.

Gradle also uses codebased build scripts instead of XML-files which is easier.

Overall Gradle outperforms Maven.