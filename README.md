# 📌 Technical Test(Junior Backend Enginner)
This project is a RESTful API developed with Java 22 and Spring Boot, for managing a To-Do List with CRUD operations, validations, error handling, and in-memory persistence using H2 + JPA. It's documented with Swagger and tested with JUnit5 + Mockito.
## 🚀 Technologies Used
- Java 22
- Spring Boot 3.5.3
- Spring Web
- Spring Data JPA
- H2 Database
- Bean Validation
- Swagger / OpenAPI (springdoc-openapi)
- JUnit 5
- Mockito

## 📁 Project Structure
```plaintext
src/
├── main/
│   ├── java/org/storehq/todoapi/
|   │   ├── exception/
|   |   |   └── ApiError.java
|   |   |   └── GlobalExceptionHandler.java
|   |   |   └── ResourceNotFoundException.java
│   │   ├── module/
│   │   |   |── task/
│   │   |   |   |── controller/
|   |   |   |   |   └── TaskController.java
│   │   |   |   |── entity/
|   |   |   |   |   └── Task.java
│   │   |   |   |── mapper/
|   |   |   |   |   └── TaskRequestDTO.java
│   │   |   |   |── repository/
|   |   |   |   |   └── TaskRepository.java
│   │   |   |   |── service/
|   |   |   |   |   └── TaskService.java
│   │   |   |── user/
│   │   └── TodoApiApplication.java
│   └── resources/
│       └── application.properties
├── test/
│   └── java/org/storehq/todoapi/service/TaskServiceTest.java
```

## 🧪 Available Endpoints

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| GET | `/tasks` | Lista todas las tareas |
| GET | `/tasks/{id}` | Obtener tarea por ID |
| POST | `/tasks` | Crear una nueva tarea |
| PUT | `/tasks/{id}` | Actualizar una tarea |
| DELETE | `/tasks/{id}` | Eliminar una tarea |

## 🧾 Expected JSON format
```plaintext
{
  "title": "Aprender Spring Boot",
  "description": "Completar el tutorial oficial",
  "done": false
}
```
## 📘 Swagger Documentation
```plaintext
http://localhost:8080/swagger-ui.html
```
## 🧪 H2 Database
```plaintext
http://localhost:8080/h2-console
```
**Credentials:**
- JDBC URL : jdbc:h2:mem:tasksdb
- User : sa
## Execution

This project was developed in IntelliJ IDEA. To run it from the command line, navigate to the project's root path and use the following commands:
```plaintext
# Run the application
./mvnw spring-boot:run
# Run the tests
./mvnw test

```
