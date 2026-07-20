# URL Shortener

[![CI](https://github.com/Ali0-6/urlShortener/actions/workflows/ci.yml/badge.svg)](https://github.com/Ali0-6/urlShortener/actions/workflows/ci.yml)

A backend service that shortens URLs, redirects users to the original link, and tracks click analytics — built with Java and Spring Boot.

## Tech Stack
- Java 26
- Spring Boot 4
- PostgreSQL (Spring Data JPA / Hibernate)
- Docker & Docker Compose
- Maven
- JUnit & Mockito
- GitHub Actions (CI/CD)
- Bucket4j (rate limiting)

## Features
- Create shortened URLs via a REST API
- Redirect from short codes to original URLs
- Click tracking per short URL
- Input validation and centralized error handling
- Rate limiting (10 requests/minute per IP)
- Layered architecture (Controller → Service → Repository)

## Architecture
Requests flow through a layered structure: Controllers handle HTTP only, Services contain business logic, Repositories (Spring Data JPA) handle persistence. DTOs decouple the API's public shape from the database entity, preventing mass assignment vulnerabilities.

## API Endpoints

POST /api/urls Create a short URL
GET /{shortCode} Redirect to the original URL

**Example request:**
```json
POST /api/urls
{
    "originalUrl": "https://www.example.com"
}
```

**Example response:**
```json
{
    "shortCode": "aZ3kD9",
    "originalUrl": "https://www.example.com",
    "shortUrl": "http://localhost:8080/aZ3kD9"
}
```

## Running the project

### Option 1: Docker (recommended)
1. Clone the repo
2. Create a `.env` file in the project root:

DB_PASSWORD=your_chosen_password

3. Run:

docker compose up

4. App runs at `http://localhost:8080`

### Option 2: Manual local setup
1. Create PostgreSQL databases named `urlshortener` and `urlshortener_test`
2. Set environment variables `DB_USERNAME` and `DB_PASSWORD`
3. Run:

./mvnw spring-boot:run

4. App runs at `http://localhost:8080`

## Running Tests

./mvnw test

Includes unit tests (Mockito-mocked service logic) and integration tests (full Spring context with a real test database, using `@ActiveProfiles("test")`).

## CI/CD
Every push to `main` triggers a GitHub Actions workflow that:
- Spins up a temporary PostgreSQL service
- Runs the full test suite
- Builds the application JAR
- Builds the Docker image

## Known Limitations / Future Improvements
- `ddl-auto=update` is used for convenience; a production version would use Flyway/Liquibase migrations
- No authentication — any client can create or view any short URL
- Rate limiting state is in-memory per instance (not shared across multiple instances)
- Redis caching planned for the redirect lookup path to reduce database load
- Basic URL format validation, not a full RFC-compliant parser

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
- [x] CI/CD (GitHub Actions)
