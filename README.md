# Java AI Document Intelligence

Java 21 Spring Boot **starter** by Sumana Sree Maruthu.

**Status:** REST API demo only. Gemini integration, AI agents, RAG, vector search, databases, authentication, and frontend are planned, **not implemented**.

## Run
Requires Java 21 and Maven 3.9+.
```bash
mvn spring-boot:run
mvn test
curl -X POST http://localhost:8080/api/v1/documents -H 'Content-Type: application/json' -d '{"documentName":"example"}'
```

## Roadmap
- [ ] Gemini API integration with secure environment variables
- [ ] Service and repository layers
- [ ] Persistent database and migrations
- [ ] Integration and security tests
- [ ] React or Angular frontend
- [ ] Docker Compose and deployment

Use only synthetic data.
