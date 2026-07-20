# URL Shortener

A backend service that shortens long URLs, built with Java and Spring Boot.

## Status
🚧 Work in progress — currently learning Java/Spring Boot by building this from scratch.

## Tech Stack
- Java 26
- Spring Boot 4
- PostgreSQL
- Redis (planned)
- Docker & Docker Compose
- Maven

## Running the project

### Option 1: Docker (recommended)
1. Clone the repo
2. Create a `.env` file in the project root with:

DB_PASSWORD=your_chosen_password

3. Run `docker compose up`
4. App starts on `http://localhost:8080`

### Option 2: Manual local setup
1. Create PostgreSQL databases named `urlshortener` and `urlshortener_test`
2. Set environment variables `DB_USERNAME` and `DB_PASSWORD`
3. Run `./mvnw spring-boot:run` (or run `UrlShortenerApplication.java` directly from an IDE)
4. App starts on `http://localhost:8080`

## Running tests
Run `UrlServiceTest` and `UrlControllerIntegrationTest` from your IDE, or `./mvnw test`

## Roadmap
- [x] Project setup with Spring Boot
- [x] Create short URL endpoint
- [x] Redirect endpoint
- [x] Database integration (PostgreSQL)
- [x] Click analytics
- [x] Input validation and error handling
- [x] Unit and integration tests
- [x] Rate limiting
- [x] Docker & Docker Compose
- [ ] CI/CD (GitHub Actions)