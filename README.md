# Quotes Project

A Spring Boot backend service for managing and retrieving quotes, built as part of a meta coding challenge. The project provides RESTful APIs to fetch, search, and manage quotes stored in a MongoDB database, with caching for improved performance.

## Features
- **Spring Boot 3** backend
- **MongoDB** integration for persistent storage
- **RESTful API** for accessing quotes
- **Caching** (Caffeine) for faster repeated queries
- **Dockerized** for easy deployment
- **Lombok** for reduced boilerplate

## API Endpoints
- `GET /api/quotes` — Get all quotes
- `GET /api/quotes/{id}` — Get a quote by its ID
- `GET /api/quotes/author/{author}` — Get all quotes by a specific author
- `GET /hello` — Simple health check endpoint

## Getting Started

### Prerequisites
- Java 17+
- Gradle (wrapper included)
- MongoDB instance (local or remote)
- Docker (optional, for containerization)

### Build & Run (Locally)
```bash
./gradlew build
java -jar build/libs/quotes-0.0.1-SNAPSHOT.jar
```

### Run with Docker
```bash
docker build -t tui/meta-challenge .
docker run -p 8080:8080 tui/meta-challenge
```

### Configuration
- The application expects a running MongoDB instance. Configure the MongoDB URI and other properties in `application.properties` or via environment variables as needed.

## Project Structure
```
quotes-project/
├── src/main/java/tui/meta/challenge/quotes/
│   ├── controller/   # REST controllers
│   ├── service/      # Business logic
│   ├── repository/   # MongoDB repositories
│   ├── model/        # Data models
│   ├── dto/          # Data transfer objects
│   ├── config/       # Configuration (MongoDB, caching)
│   └── exception/    # Exception handling
├── src/test/         # Tests
├── build.gradle      # Gradle build file
├── Dockerfile        # Docker setup
└── README.md         # Project documentation
```



