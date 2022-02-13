# Mortage Plan - Code Test
[![CircleCI](https://circleci.com/gh/Erru17/Mortage-plan-test.svg?style=shield&circle-token=c3284a727732685a844ed772b8d55eb1ad060d2c)](https://circleci.com/)

This application creates Customers from a text file.  
The file contains customer name, total loan amount, interest rate and loan period.  
All of the customers then get their monthly mortgage calculated.  
There is a terminal- and a web version. The web version also allows the user to put in their own values.  
  
The web version is also hosted on AWS.  

## Table of Contents  
* [Technologies](https://github.com/Erru17/Mortage-plan-test#technologies)
* [Public Cloud Service (AWS)](https://github.com/Erru17/Mortage-plan-test#public-cloud-service-aws)
* [Installation](https://github.com/Erru17/Mortage-plan-test#installation)
  * [Run Program (Terminal)](https://github.com/Erru17/Mortage-plan-test#run-program-terminal)
    * [CMD (Windows)](https://github.com/Erru17/Mortage-plan-test#cmd-windows)
    * [UNIX (Linux/Mac)](https://github.com/Erru17/Mortage-plan-test#unix-linuxmac)
  * [Run Server (Browser)](https://github.com/Erru17/Mortage-plan-test#run-server-browser)
    * [CMD (Windows)](https://github.com/Erru17/Mortage-plan-test#cmd-windows-1)
    * [UNIX (Linux/Mac)](https://github.com/Erru17/Mortage-plan-test#unix-linuxmac-1)
  * [Docker Server](https://github.com/Erru17/Mortage-plan-test#docker-server)
* [Future Plans](https://github.com/Erru17/Mortage-plan-test#future-plans)
* [Choices](https://github.com/Erru17/Mortage-plan-test#choices)
  * [Name formatting](https://github.com/Erru17/Mortage-plan-test#name-formatting)
  * [Why gradle?](https://github.com/Erru17/Mortage-plan-test#why-gradle)

## Technologies
* Java
* HTML / CSS
* Gradle
* Spring Boot
* Bootstrap
* Docker, Docker-compose
* AWS - Elastic Beanstalk

## Public Cloud Service (AWS)
[Online Demo](http://mortageplan-env.eba-jpr8vjbf.eu-north-1.elasticbeanstalk.com/)  

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
--------------------

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
> [Github Issue](https://github.com/hamvocke/spring-testing/issues/3)  
> [Stack Overflow](https://stackoverflow.com/questions/34724299/why-does-my-spring-boot-web-app-not-run-completely-in-gradle)  

--------------------

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
