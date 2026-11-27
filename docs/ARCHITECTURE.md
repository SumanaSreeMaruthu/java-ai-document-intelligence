# Architecture

## Existing application flow

Browser UI → ApiController → DocumentService → Spring Data JPA repository → H2 database.

## Additional packages

- `controller/`: health-check endpoint and future domain-specific controllers
- `service/`: read-only service contract
- `service/impl/`: adapter that delegates to existing domain service
- `dto/`: response DTO types for future controller refactoring
- `exception/`: common validation and HTTP exception handling
- `config/`: local frontend CORS configuration

## Limitations

The primary domain classes still reside in `dev.sumana.portfolio`. The new service interface and implementation are adapters, not a completed refactor of all domain operations. Additional packages do not by themselves establish a production-ready architecture. Tests and Maven builds require independent verification.
