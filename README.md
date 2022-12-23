# Spring Boot with Kotlin

## Required Software, Tools and Version
* Java JDK Version: 17 (`java -version`)
* Docker Version: 19 (`docker --version`)
* Git Client: Any latest version (`git --version`)
* Integrated Development Environment: Any version of IntelliJ Idea or Eclipse

## Commands

### Build (Unit and Integration tests)

    ./gradlew clean build

### Spring Boot Run
    
    ./gradlew bootRun
    
### Acceptance tests

    ./gradlew clean assemble
    
    docker build --pull -t com.harishkannarao/kotlin-spring-boot:latest -f application/Dockerfile application/build/libs
    
    docker run --rm -d --name kotlin-spring-boot -p '8080:8080' com.harishkannarao/kotlin-spring-boot:latest
    
    ./gradlew clean acceptanceTest
    
    docker stop kotlin-spring-boot

### Change project version

    ./gradlew clean build -PprojectVersion=1.0.9
