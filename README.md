# Quiz Management Service

## Overview
The Quiz Management Service is a microservice designed to handle the creation, management, and evaluation of quizzes. This service follows a RESTful architecture with versioning and provides endpoints for managing quizzes, questions, and results.

---

## Features
- **Quiz Management**: Create, update, delete, and retrieve quizzes.
- **Question Management**: Add, update, delete, and retrieve questions for quizzes.
- **Quiz Attempts**: Submit and track quiz attempts.
- **Results**: Evaluate and retrieve quiz results.
- **Validation**: Comprehensive validation for all incoming data.
- **Scalability**: Designed for microservices architecture with modular components.

---

## Technologies and Tools
### Backend
- **Programming Language**: Java
- **Framework**: Spring Boot (REST API Development)
- **Architecture**: Microservices
- **Database**: MySQL (via Spring Data JPA)

### Utilities
- **Model Mapping**: ModelMapper (Mapping POJOs, DTOs, and Entities)
- **Code Reduction**: Lombok (Reducing boilerplate code)
- **Logging**: SLF4J (Structured logging for debugging and monitoring)
- **Data Conversion**: Gson (Convert Java objects to JSON strings and vice versa)

### Security
- **Authentication and Authorization**: OAuth 2.0
- **Data Security**: HMAC SHA-256 (Securing sensitive user data)

---

## Project Structure
```
com.example.quizattemptservice
    ├── controller
    │     ├── QuizController.java
    │     ├── QuizAttemptController.java
    │     ├── QuestionAttemptController.java
    │     ├── QuizResultController.java
    │
    ├── service
    │     ├── QuizAttemptService.java
    │     ├── QuizResultService.java
    │     ├── QuestionAttemptService.java
    │     ├── impl
    │           ├── QuizAttemptServiceImpl.java
    │           ├── QuizResultServiceImpl.java
    │           ├── QuestionAttemptServiceImpl.java
    │
    ├── repository
    │     ├── QuizAttemptRepository.java
    │     ├── QuestionAttemptRepository.java
    │     ├── QuizResultRepository.java
    │
    ├── model
    │     ├── QuizAttempt.java
    │     ├── QuestionAttempt.java
    │     ├── QuizResult.java
    │     ├── User.java
    │
    ├── dto
    │     ├── QuizAttemptRequestDTO.java
    │     ├── QuizAttemptResponseDTO.java
    │     ├── QuestionAttemptDTO.java
    │     ├── QuizResultDTO.java
    │     ├── UserDTO.java
    │
    ├── mapper
    │     ├── QuizAttemptMapper.java
    │     ├── QuestionAttemptMapper.java
    │     ├── QuizResultMapper.java
    │     ├── UserMapper.java
    │
    ├── exception
    │     ├── QuizAttemptException.java
    │     ├── QuestionAttemptException.java
    │     ├── QuizResultException.java
    │     ├── ErrorDetails.java
    │
    ├── config
    │     ├── AppConfig.java
    │     ├── SwaggerConfig.java
    │
    ├── util
    │     ├── QuizAttemptUtils.java
    │     ├── ScoringUtils.java
    │     ├── TimeUtils.java
    │
    ├── listener
    │     ├── QuizAttemptListener.java
    │
    ├── validator
    │     ├── QuizValidator.java
    │     ├── QuestionValidator.java
    │
    ├── QuizAttemptServiceApplication.java
```

---

## Key Design Decisions
1. **3-Tier Architecture**:
   - **Controller Layer**: Handles API requests and responses.
   - **Service Layer**: Contains business logic and DTO transformations.
   - **Repository Layer**: Manages database interactions using JPA.

2. **Data Mapping**:
   - POJOs in Controller Layer.
   - DTOs in Service Layer.
   - Entities in Repository Layer.

3. **API Standards**:
   - RESTful endpoints.
   - Versioning (e.g., `/api/v1/quizzes`).

4. **Error Handling**:
   - Custom exceptions for better error clarity.
   - Standardized error responses using `ErrorDetails`.

---

## Deployment and Scalability
- **Containerization**: Ready for Docker and Kubernetes (future expansion).
- **Scalable Database**: MySQL with proper indexing for performance.

---

## Future Enhancements
- Integration with the User Management Service.
- Real-time quiz evaluations.
- Docker and Kubernetes for orchestration.
- Enhanced analytics and reporting.

---

## Contributing
We welcome contributions to improve the Quiz Management Service! Please follow our [Contribution Guidelines](CONTRIBUTING.md).

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

For inquiries, please contact:

- **Email**: [Mail Me](shaikameerjann@gmail.com)
- **LinkedIn**: [Connect](https://www.linkedin.com/in/ameer-shaikk/)


