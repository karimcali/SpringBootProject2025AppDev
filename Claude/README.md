# Planets and Moons API

A comprehensive Spring Boot REST API for managing planets and moons with GraphQL support, Spring Security, and complete Swagger documentation.

## Features

- **RESTful API** with full CRUD operations for Planets and Moons
- **GraphQL API** for user queries and mutations
- **Spring Security** with Basic Authentication and role-based access control
- **Role-based Authorization**:
  - `ADMIN`: Full access to all endpoints
  - `STAFF`: Create, edit, and delete operations
  - `STUDENT`: Read-only access
- **H2 In-Memory Database** for easy testing
- **Swagger/OpenAPI Documentation** for API exploration
- **AspectJ Logging** for monitoring key operations
- **Centralized Exception Handling** with detailed error responses
- **DTO Pattern** for clean separation of concerns
- **Bean Validation** for input validation
- **Sample Data Preloading** for testing

## Technologies

- **Java 17**
- **Spring Boot 3.3.5**
- **Spring Data JPA**
- **Spring Security**
- **Spring GraphQL**
- **H2 Database**
- **SpringDoc OpenAPI 2.7.0**
- **Lombok**
- **Maven**

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Running the Application

1. Clone the repository:
```bash
git clone <repository-url>
cd Claude-Prompt
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

### Swagger UI

Access the interactive API documentation at:
```
http://localhost:8080/swagger-ui.html
```

### H2 Database Console

Access the H2 console at:
```
http://localhost:8080/h2-console
```
- **JDBC URL**: `jdbc:h2:mem:planetsdb`
- **Username**: `sa`
- **Password**: (leave empty)

### GraphiQL Interface

Access the GraphQL playground at:
```
http://localhost:8080/graphiql
```

## Authentication

The API uses HTTP Basic Authentication. Use the following test credentials:

| Username | Password    | Role    |
|----------|-------------|---------|
| admin    | admin123    | ADMIN   |
| staff    | staff123    | STAFF   |
| student  | student123  | STUDENT |

### Example Authentication (curl)

```bash
curl -u admin:admin123 http://localhost:8080/api/planets
```

## REST API Endpoints

### Planets

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/planets` | Get all planets | ADMIN, STAFF, STUDENT |
| GET | `/api/planets/{id}` | Get planet by ID | ADMIN, STAFF, STUDENT |
| GET | `/api/planets/type/{type}` | Filter planets by type | ADMIN, STAFF, STUDENT |
| GET | `/api/planets/{id}/moon-count` | Count moons for a planet | ADMIN, STAFF, STUDENT |
| POST | `/api/planets` | Create new planet | ADMIN, STAFF |
| PUT | `/api/planets/{id}` | Update planet | ADMIN, STAFF |
| DELETE | `/api/planets/{id}` | Delete planet | ADMIN, STAFF |

### Moons

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/moons` | Get all moons | ADMIN, STAFF, STUDENT |
| GET | `/api/moons/{id}` | Get moon by ID | ADMIN, STAFF, STUDENT |
| GET | `/api/moons/planet/{planetId}` | Get moons by planet | ADMIN, STAFF, STUDENT |
| POST | `/api/moons` | Create new moon | ADMIN, STAFF |
| PUT | `/api/moons/{id}` | Update moon | ADMIN, STAFF |
| DELETE | `/api/moons/{id}` | Delete moon | ADMIN, STAFF |

### Users

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/users` | Get all users | ADMIN |
| GET | `/api/users/{id}` | Get user by ID | ADMIN |
| POST | `/api/users` | Create new user | ADMIN |
| DELETE | `/api/users/{id}` | Delete user | ADMIN |

## GraphQL API

### Queries

**Find User by ID:**
```graphql
query {
  userById(id: 1) {
    id
    username
    role
  }
}
```

### Mutations

**Create User (Admin only):**
```graphql
mutation {
  createUser(input: {
    username: "newuser"
    password: "password123"
    role: "STUDENT"
  }) {
    id
    username
    role
  }
}
```

## Example API Requests

### Create a Planet

```bash
curl -X POST http://localhost:8080/api/planets \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Venus",
    "type": "Terrestrial",
    "radiusKm": 6051.8,
    "massKg": 4.867e24,
    "orbitalPeriodDays": 224.7
  }'
```

### Get Planets by Type

```bash
curl -u student:student123 \
  http://localhost:8080/api/planets/type/Gas%20Giant
```

### Create a Moon

```bash
curl -X POST http://localhost:8080/api/moons \
  -u staff:staff123 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Enceladus",
    "diameterKm": 504.2,
    "orbitalPeriodDays": 1.370,
    "planetId": 4
  }'
```

### Get Moons by Planet

```bash
curl -u student:student123 \
  http://localhost:8080/api/moons/planet/3
```

### Count Moons for a Planet

```bash
curl -u student:student123 \
  http://localhost:8080/api/planets/3/moon-count
```

## Sample Data

The application preloads the following sample data:

### Planets
- Earth (Terrestrial)
- Mars (Terrestrial)
- Jupiter (Gas Giant)
- Saturn (Gas Giant)
- Neptune (Ice Giant)

### Moons
- Luna (Earth)
- Phobos, Deimos (Mars)
- Io, Europa, Ganymede, Callisto (Jupiter)
- Titan (Saturn)
- Triton (Neptune)

## Project Structure

```
src/main/java/com/planets/api/
├── aspect/          # AspectJ logging
├── config/          # Configuration classes
├── controller/      # REST controllers
├── dto/             # Data Transfer Objects
├── entity/          # JPA entities
├── exception/       # Custom exceptions and handlers
├── graphql/         # GraphQL resolvers
├── repository/      # JPA repositories
└── service/         # Business logic layer

src/main/resources/
├── application.yml  # Application configuration
└── graphql/
    └── schema.graphqls  # GraphQL schema
```

## Architecture

The application follows a **layered architecture**:

1. **Controller Layer**: Handles HTTP requests and responses
2. **Service Layer**: Contains business logic
3. **Repository Layer**: Data access with Spring Data JPA
4. **DTO Layer**: Data transfer objects for API communication
5. **Entity Layer**: JPA entities for database mapping

## Security

- **Authentication**: HTTP Basic Authentication
- **Authorization**: Role-based access control with method security
- **Password Encryption**: BCrypt password encoding
- **CSRF**: Disabled for stateless API

## Logging

AspectJ is configured to log:
- All controller method invocations with parameters
- Service method execution time
- Exceptions thrown in controllers and services

## Error Handling

Centralized exception handling provides consistent error responses:

```json
{
  "timestamp": "2025-12-03T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Planet not found with id: '999'",
  "path": "/api/planets/999"
}
```

## Validation

Input validation is enforced using Jakarta Bean Validation:
- Required fields
- Positive numbers for measurements
- String length constraints

## License

This project is licensed under the Apache License 2.0.

## Support

For questions or issues, please contact support@planets-api.com
