# Spring Boot with Kotlin

## Required Software, Tools and Version
* Java JDK Version: 11 Adopt OpenJDK (`java -version`)
* Git Client: Any latest version (`git --version`)
* Integrated Development Environment: Any version of IntelliJ Idea or Eclipse (with `Annotations Processing` enabled for `Immutables` library)

## Commands

### Build (Unit and Integration tests)

    ./gradlew clean build

### Spring Boot Run
    
    ./gradlew bootRun
    
### Acceptance tests

Terminal 1:

    ./gradlew bootRun
    
Terminal 2:

    ./gradlew clean acceptanceTest    
