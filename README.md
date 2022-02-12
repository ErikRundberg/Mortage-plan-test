# Mortage Plan code test

The application creates Customers from text file and calculates all the customers' monthly mortgage cost and presents it.  

## Table of Contents  
* [Technologies](https://github.com/Erru17/Mortage-plan-test#technologies)
* Public Cloud Service (AWS)
* Installation
  * Run Program (Terminal)
    * CMD (Windows)
    * UNIX (Linux/Mac)
  * Run Server
    * CMD (Windows)
    * UNIX (Linux/Mac)
  * Docker Server
* Future Plans
* Choices
  * Name formatting
  * Why gradle?

## Technologies
* Java
* HTML / CSS
* Gradle
* Spring Boot
* Bootstrap
* Docker, Docker-compose
* AWS - Elastic Beanstalk

## Public Cloud Service (AWS)
http://mortageplan-env.eba-jpr8vjbf.eu-north-1.elasticbeanstalk.com/  

## Installation

### Run Program (Terminal)

#### CMD (Windows)

```cmd
gradlew cli
```
#### UNIX (Linux/Mac)

```bash
./gradlew cli
```

### Run Server (Browser)

#### CMD (Windows)

```cmd
gradlew bootRun
```
#### UNIX (Linux/Mac)

```bash
./gradlew bootRun
```

Go to: http://localhost:8080

> It's normal to get stuck at ~80%, the server is still running.
>
> https://stackoverflow.com/questions/34724299/why-does-my-spring-boot-web-app-not-run-completely-in-gradle

### Docker Server

```docker
docker-compose up
```
Go to: http://localhost:8080

## Future plans

- [ ] Testing
- [x] Web interface
- [x] Amazon AWS
- [x] Docker
- [x] Gradle

## Choices

### Name formatting
I decided to keep `"Clarencé,Andersson"` as it was written instead of removing the quotation marks and the comma to show that I can handle them.

In production I would have formatted it into `Clarencé Andersson` to keep it consistent with the other entries in prospects.txt, making it easier for database integration.

### Why Gradle?
I chose Gradle over Maven mainly for their incremental build which makes rebuilding the application way faster as only changed files have to be rebuilt.

Gradle also uses codebased build scripts instead of XML-files which is easier.

Overall Gradle outperforms Maven.