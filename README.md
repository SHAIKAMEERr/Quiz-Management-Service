# Quiz Management Service

## Overview
The Quiz Management Service is a backend application designed to manage quizzes, questions, and user responses efficiently. It provides RESTful APIs for creating, updating, and retrieving quizzes and their associated details.

---

## Features
- Create, update, and delete quizzes.
- Add, update, and delete questions within quizzes.
- Fetch quizzes with their associated questions.
- Manage user responses and scores.
- Role-based access control for users (Admin, Instructor, Student).

---

## Architecture
This service is developed using:
- **Java** (Backend logic)
- **Spring Boot** (Framework for rapid application development)
- **MySQL** (Database for persistent storage)
- **Spring Security** (For authentication and role-based authorization)
- **REST API** (For seamless integration with front-end applications)

---

## Prerequisites
- Java 17
- Maven 3.8+
- MySQL 8+
- Postman (Optional, for API testing)

---

## Installation and Setup

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/quiz-management-service.git
cd quiz-management-service
```

### 2. Configure the Database
1. Create a database in MySQL:
   ```sql
   CREATE DATABASE quiz_management;
   ```
2. Update the database configuration in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/quiz_management
   spring.datasource.username=<your_db_username>
   spring.datasource.password=<your_db_password>
   spring.jpa.hibernate.ddl-auto=update
   ```

### 3. Build the Application
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

### 5. Access the Application
- Swagger UI (API Documentation): [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## API Endpoints

### Authentication
- **POST** `/api/v1/auth/login` - User login
- **POST** `/api/v1/auth/register` - User registration

### Quiz Management
- **POST** `/api/v1/quizzes` - Create a new quiz
- **GET** `/api/v1/quizzes` - Get all quizzes
- **GET** `/api/v1/quizzes/{id}` - Get a specific quiz
- **PUT** `/api/v1/quizzes/{id}` - Update a quiz
- **DELETE** `/api/v1/quizzes/{id}` - Delete a quiz

### Question Management
- **POST** `/api/v1/quizzes/{quizId}/questions` - Add questions to a quiz
- **GET** `/api/v1/quizzes/{quizId}/questions` - Get all questions of a quiz
- **PUT** `/api/v1/questions/{id}` - Update a question
- **DELETE** `/api/v1/questions/{id}` - Delete a question

### User Responses
- **POST** `/api/v1/quizzes/{quizId}/responses` - Submit responses for a quiz
- **GET** `/api/v1/quizzes/{quizId}/scores` - Get scores for a quiz

---

## Technologies Used
- **Java 17**
- **Spring Boot 3.0**
- **MySQL 8.0**
- **Swagger** (For API documentation)
- **Lombok** (For reducing boilerplate code)

---

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request with your changes.

---

## License
This project is licensed under the MIT License. See the LICENSE file for more details.

---

## Contact
For queries or issues, contact:
- Email: [Mail-Me](shaikameerjann@gmail.com)
- LinkedIn: [Connect With Me](https://linkedin.com/in/ameer-shaikk/)
