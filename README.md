Springboot project for online assesment\

Steps to run using docker:-\
Execute the commands in the root directory of the project:-\
`1.` docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-docker .\
`2.` docker build -t springio/gs-spring-boot-docker .\
`3.` docker-compose up \

Make sure you add database url, username and password\
in a `.env` file.\

Steps to execute the project without docker:-\

`1.` ./gradlew bootJar\
`2.` java -jar ./build/libs/grocery-0.0.1-SNAPSHOT.jar\