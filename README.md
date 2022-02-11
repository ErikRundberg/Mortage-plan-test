# Mortage Plan code test

The application creates Customers from text file and calculates all the customers' monthly mortgage cost and presents it.  
The application works both on the web and in terminal.

## Technologies
* Java
* Gradle
* Spring Boot
* Bootstrap

## Installation
To run the application do the following:
Make sure you have a version of Java installed
Be in root folder

### Run program (Terminal)

#### CMD

```cmd
gradlew cli
```
#### UNIX

```bash
./gradlew cli
```

### Run server (Browser)

#### CMD

```cmd
gradlew bootRun
```
#### UNIX

```bash
./gradlew bootRun
```

Go to: http://localhost:8080

> It's normal to get stuck at ~80%, the server is still running.
>
> https://stackoverflow.com/questions/34724299/why-does-my-spring-boot-web-app-not-run-completely-in-gradle

## Future plans

* Testing
* Docker
* Web interface (Spring Boot)
* ~~Gradle~~

## Choices

### Name formatting from file
I decided to keep `"Clarencé,Andersson"` as it was written instead of removing the quotation marks and the comma to show that I can handle them.

I probably would've formatted it into `Clarencé Andersson` to keep it consistent with other entries in prospects.txt.

### Why Gradle?
I chose Gradle over Maven mainly for their incremental build which makes rebuilding the application way faster as only changed files have to be rebuilt.

Gradle also uses codebased build scripts instead of XML-files which is easier.

Overall Gradle outperforms Maven.