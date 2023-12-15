# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [v2.0.0] - 2023-12-12
### Added
- Prometheus' metrics endpoint.
- Checkstyle for code quality.
- Add support for GitHub Actions for CI/CD.
- Enabled centralized cross-origin resource sharing (CORS) to allow frontend applications to call the endpoints.
- Set the frontend URL dynamically through an external environment variable, enhancing configuration flexibility.
- Added SonarCloud for code quality.

### Modified
- Update files to conform to checkstyle rules.
- Spring MVC to Spring WebFlux.
- Refactor code to use reactive programming.

### Fixed
- Fix bug in the creation of the user.

## [v1.0.0] - 2023-12-4

### Added
- User Creation feature enabling secure profile creation for each user in the identity provider.
- Integration with Keycloak for seamless user management and authentication.
- Environment variable configuration for integrating with existing Keycloak instances and Wallet-Data component.
- Docker-compose configuration for easy deployment and setup
- Project status, contact information, and creation/update dates in README.

[release]:
[1.0.0]: https://github.com/in2workspace/wallet-user-registry/releases/tag/v1.0.0
[2.0.0]: https://github.com/in2workspace/wallet-user-registry/releases/tag/v2.0.0
