# Java AI Document Intelligence

Java 21 / Spring Boot document-text Q&A demonstration.

## Implemented
- Save, list, retrieve and delete pasted text documents in H2/JPA
- Ask Gemini 2.5 Flash questions about a stored document
- Browser interface at `http://localhost:8080/`
- REST `POST /api/v1/documents` and `POST /api/v1/documents/{id}/ask`
- Basic context test, Dockerfile and CI

## Run
```bash
export GEMINI_API_KEY=your_key_here
mvn spring-boot:run
```

## Limitations
This is **not a full RAG/document-ingestion platform**. It does not yet parse PDFs, accept uploaded files, use embeddings/pgvector, provide citations, enforce authentication, or offer a React frontend. Full document text is sent to Gemini, so use synthetic, non-sensitive data only. Build has not been independently verified.
