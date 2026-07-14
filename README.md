# URL Shortener

A backend service that shortens long URLs, built with Java and Spring Boot.

## Status
🚧 Work in progress — currently learning Java/Spring Boot by building this from scratch.

## Tech Stack
- Java 17
- Spring Boot
- Maven

## Running the project
1. Clone the repo
2. Create a PostgreSQL database named `urlshortener`
3. Set environment variables `DB_USERNAME` and `DB_PASSWORD` (matching your local Postgres credentials)
4. Run `./mvnw spring-boot:run` (or run `UrlShortenerApplication.java` directly from an IDE)
5. App starts on `http://localhost:8080`

## Roadmap
- [x] Project setup with Spring Boot
- [x] Create short URL endpoint
- [ ] Redirect endpoint
- [x] Database integration (PostgreSQL)
- [ ] Click analytics
- [ ] Caching (Redis)
- [ ] Rate limiting
- [ ] Docker + CI/CD