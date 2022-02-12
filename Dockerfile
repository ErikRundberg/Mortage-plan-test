FROM gradle:jdk-alpine

WORKDIR /home/gradle/bank

EXPOSE 8080

USER root

RUN apk update

ENV GRADLE_USER_HOME /home/gradle/bank

COPY . /home/gradle/bank

RUN ./gradlew build


FROM java:jre-alpine

WORKDIR /home/gradle/bank

COPY --from=0 /home/gradle/bank/build/libs/bank-0.0.1-SNAPSHOT.jar .

ENTRYPOINT java -jar bank-0.0.1-SNAPSHOT.jar