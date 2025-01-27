# Quiz Management Service

The **Quiz Management Service** is a comprehensive RESTful microservice for managing quizzes, quiz attempts, and results. Built with **Java** and **Spring Boot**, it provides a scalable and secure platform that adheres to best practices for API development, ensuring seamless integration and maintainability.

## Key Features

### 1. Quiz Management
- Create, update, delete, and retrieve quizzes.
- Manage quiz questions, options, and scoring rules.
- Enforce time limits and other quiz rules.

### 2. Quiz Attempt Handling
- Allow users to attempt quizzes with real-time tracking.
- Validate answers and compute scores dynamically.
- Store and retrieve quiz results with detailed insights.

### 3. User Integration
- Integrates with the **User Management Service** for user authentication and role-based access control (RBAC).

### 4. Security
- Uses **OAuth 2.0** for secure authentication.
- Implements **HMAC SHA-256** to safeguard sensitive data.

### 5. Standards Compliance
- RESTful API standards with proper versioning and naming conventions.
- Detailed Swagger documentation for easy API consumption.

## Technologies Used

### Backend
- **Java** (Core backend programming)
- **Spring Boot** (Web, Data JPA, Security, Validation)
- **ModelMapper** (Object mapping)
- **Lombok** (Simplifies code with annotations)
- **SLF4J** (Logging framework)
- **Gson** (JSON serialization/deserialization)

### Database
- **MySQL** (Relational database)
- **Spring Data JPA** (Database interaction)

### Security
- **OAuth 2.0** (Authentication)
- **HMAC SHA-256** (Data security)

### Testing & Documentation
- **JUnit** & **Mockito** (Unit and integration testing)
- **Swagger UI** (API documentation)

## Architecture

The service is designed with a **3-tier architecture**:

1. **Controller Layer**
   - Handles user input and returns API responses.
   - Accepts POJOs and returns DTOs.

2. **Service Layer**
   - Implements business logic.
   - Maps POJOs to DTOs and vice versa using **ModelMapper**.

3. **Repository Layer**
   - Interacts with the database.
   - Maps DTOs to DAOs/Entities using **Spring Data JPA**.

## Packaging Structure

The project uses the following packaging structure to ensure modularity and maintainability:

```
com.example.quizmanagementservice
    ├── controller
    │     ├── QuizController.java
    │     ├── QuizAttemptController.java
    │     ├── QuizResultController.java
    │
    ├── service
    │     ├── QuizService.java
    │     ├── QuizAttemptService.java
    │     ├── QuizResultService.java
    │     ├── impl
    │           ├── QuizServiceImpl.java
    │           ├── QuizAttemptServiceImpl.java
    │           ├── QuizResultServiceImpl.java
    │
    ├── repository
    │     ├── QuizRepository.java
    │     ├── QuizAttemptRepository.java
    │     ├── QuizResultRepository.java
    │
    ├── model
    │     ├── Quiz.java
    │     ├── QuizAttempt.java
    │     ├── QuizResult.java
    │
    ├── dto
    │     ├── QuizDTO.java
    │     ├── QuizAttemptDTO.java
    │     ├── QuizResultDTO.java
    │
    ├── config
    │     ├── AppConfig.java
    │     ├── SwaggerConfig.java
    │
    ├── mapper
    │     ├── QuizMapper.java
    │     ├── QuizAttemptMapper.java
    │     ├── QuizResultMapper.java
    │
    ├── exception
    │     ├── GlobalExceptionHandler.java
    │     ├── QuizException.java
    │
    ├── util
    │     ├── QuizUtils.java
    │     ├── ScoringUtils.java
    │
    ├── QuizManagementServiceApplication.java
```

## API Endpoints

The service runs on **port 8082**.

### Quiz Management Endpoints
- `POST /api/v1/quizzes` - Create a new quiz.
- `GET /api/v1/quizzes` - Retrieve all quizzes.
- `GET /api/v1/quizzes/{id}` - Retrieve a quiz by ID.
- `PUT /api/v1/quizzes/{id}` - Update an existing quiz.
- `DELETE /api/v1/quizzes/{id}` - Delete a quiz.

### Quiz Attempt Endpoints
- `POST /api/v1/quizzes/{id}/attempts` - Start a quiz attempt.
- `GET /api/v1/attempts/{id}` - Retrieve a quiz attempt.
- `POST /api/v1/attempts/{id}/submit` - Submit answers for scoring.

### Quiz Results Endpoints
- `GET /api/v1/results` - Retrieve all quiz results.
- `GET /api/v1/results/{id}` - Retrieve a specific result.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/quiz-management-service.git
   ```

2. Navigate to the project directory:
   ```bash
   cd quiz-management-service
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the Swagger UI for API documentation:
   ```
   http://localhost:8082/swagger-ui.html
   ```

## Future Enhancements

- Add support for quiz analytics and reporting.
- Enable adaptive and timed quizzes.
- Integrate with payment gateways for premium quizzes.

## License

This project is licensed under the [MIT License](LICENSE).

---

For more information, contact [Connect](https://www.linkedin.com/in/ameer-shaikk/).
