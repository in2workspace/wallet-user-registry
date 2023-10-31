# WALLET-USER-REGISTRY

## Introduction
The User-Identity microservice is a crucial component designed to create and manage users within an identity provider, in this case, Keycloak. This service plays a vital role in the security and user management of our application, allowing us to associate the data we store for each user with their token and utilize Keycloak security capabilities to protect our API endpoints.

Developed with Java 17 and Spring Boot 3.1.5, this microservice adheres to the principles of service-oriented programming and is managed using Gradle Groovy, facilitating dependency management and build processes.

## Main Features
* User Creation: Enables the creation of users in the identity provider, ensuring each user has a unique and secure profile.
* Security: Leverages Keycloak security capabilities to protect API endpoints, ensuring that only authorized users can access sensitive information.
* Integration with Keycloak: Designed to work seamlessly with Keycloak, facilitating user management and authentication.

## Flows

### Register User
In this flow, the user is persisted in two distinct locations: the Identity Provider (Keycloak) and the Data storage, both associated with the same ID for correlation purposes. Here's a diagram of the process:

[![](https://www.mermaidchart.com/raw/d2516ee3-fb6f-4381-ae66-12caddef08b3?version=v0.1&theme=light&format=svg)](https://www.mermaidchart.com/raw/d2516ee3-fb6f-4381-ae66-12caddef08b3?version=v0.1&theme=light&format=svg)

## Getting Started
### Prerequisites
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle](https://gradle.org/install/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Docker Desktop](https://www.docker.com/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Git](https://git-scm.com/)
- [Orion-LD-Adapter](https://github.com/in2workspace/in2-orionld-adapter.git)
- [Wallet-Data](https://github.com/in2workspace/wallet-data.git)

## Api references (Local-docker Environment)
* Swagger: http://localhost:8085/swagger-ui.html
* OpenAPI: http://localhost:8085/api-docs

## Document version
* v0.0.0