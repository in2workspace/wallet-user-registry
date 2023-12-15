<div style="text-align: center;">

<h1>Wallet User Registry</h1>
<span>by </span><a href="https://in2.es">in2.es</a>
<p><p>

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_wallet-user-registry&metric=security_rating)](https://sonarcloud.io/dashboard?id=in2workspace_wallet-user-registry)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_wallet-user-registry&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=in2workspace_wallet-user-registry)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_wallet-user-registry&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=in2workspace_wallet-user-registry)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_wallet-user-registry&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=in2workspace_wallet-user-registry)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_wallet-user-registry&metric=ncloc)](https://sonarcloud.io/dashboard?id=in2workspace_wallet-user-registry)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=in2workspace_wallet-user-registry&metric=alert_status)](https://sonarcloud.io/dashboard?id=in2workspace_wallet-user-registry)

</div>

## Introduction
The Wallet-User-Registry microservice is a crucial component designed to create and manage users within an identity provider, in this case, Keycloak. This service plays a vital role in the security and user management of our application, allowing us to associate the data we store for each user with their token and utilize Keycloak security capabilities to protect our API endpoints.

Developed with Java 17 and Spring Boot 3.1.5, this microservice adheres to the principles of service-oriented programming and is managed using Gradle Groovy, facilitating dependency management and build processes.

## Main Features
* User Creation: Enables the creation of users in the identity provider, ensuring each user has a unique and secure profile.
* Security: Leverages Keycloak security capabilities to protect API endpoints, ensuring that only authorized users can access sensitive information.
* Integration with Keycloak: Designed to work seamlessly with Keycloak, facilitating user management and authentication.

## Installation
### Prerequisites
- [Docker Desktop](https://www.docker.com/)
- [Git](https://git-scm.com/)

### Dependencies for Installation
To install and run the Wallet-User-Registry, you will need the following dependencies:
* Keycloak: You should have a working instance of Keycloak. For instructions on how to install and configure Keycloak, [visit their official documentation](https://www.keycloak.org/documentation.html)

* Wallet-Data: This component is necessary for managing user data. For its installation, follow the guide provided here: [Wallet-Data Configuration Component.](https://github.com/in2workspace/wallet-data)

Once you have these dependencies set up and running, you can proceed with configuring the Wallet-User-Registry.

## Configuration
Now that you have the necessary dependencies, you can configure the wallet-user-registry using the following docker-compose. Ensure to adjust the environment variables to match your Keycloak and Wallet-Data configurations.
* Wallet-User-Registry Configuration
```yaml
wallet-user-registry:
  container_name: wallet-user-registry
  image: in2kizuna/wallet-user-registry:v2.0.0 
  environment:
    SERVER_PORT: "8085"
    OPENAPI_SERVER_URL: "http://wallet-user-registry:8085"
    WALLET-DATA_URL: "http://wallet-data:8086/api/users"
    WALLET-WDA_URL: "<your-frontend-url>"
    KEYCLOAK_URL: "<your-keycloak-url>"
    KEYCLOAK_REALM: "<your-keycloak-realm>"
    KEYCLOAK_CLIENT-SECRET: "<your-keycloak-client-secret>"
    KEYCLOAK_CLIENT-ID: "<your-keycloak-client-id>"
  ports:
    - "8085:8085"
  networks:
    local_network:
```

## Project Status 
The project is currently at version **2.0.0** and is in a stable state.

## Contact
For any inquiries or collaboration, you can contact us at:
* **Email:** [info@in2.es](mailto:info@in2.es)
* **Name:** IN2, Ingeniería de la Información
* **Website:** [https://in2.es](https://in2.es)

## Creation Date and Update Dates
* **Creation Date:** November 9, 2023
* **Last Updated:** December 12, 2023