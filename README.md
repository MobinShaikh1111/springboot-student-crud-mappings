**Student Management System – Spring Boot**

This project is a Student Management Backend built using Spring Boot, Hibernate/JPA, and MySQL.
It includes CRUD operations and demonstrates how to handle entity relationships in a real-world application.

**Features :**
Manage Students, Departments, and Courses
CRUD REST APIs
One-to-Many (Student–Department)
Many-to-Many (Student–Course)
DTOs for request/response handling
ModelMapper for mapping Entities ↔ DTOs
@Transactional for safe DB operations
Clean layered architecture (Controller-Service-Repository)

**Tech Stack**
Spring Boot
Spring Data JPA (Hibernate)
MySQL
ModelMapper
Lombok
Maven
Postman (API Testing)

**Key Concepts Used**
Entity relationships in JPA
DTO pattern (avoid recursion & structure clean APIs)
Service layer business logic
Repository layer for database interaction
Exception & transaction handling
Data validation and persistence
