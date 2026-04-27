# Belajar Spring Boot - User Management REST API

Project backend REST API menggunakan **Java Spring Boot** untuk manajemen data user.
Dibuat sebagai media belajar menuju level **Backend Developer Java Spring Boot**.

---

# 🚀 Tech Stack

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Swagger OpenAPI
* IntelliJ IDEA
* Postman

---

# 📦 Features

## ✅ CRUD User

* Create User
* Get All Users
* Get User By ID
* Update User
* Delete User

## ✅ Validation

Menggunakan Bean Validation:

* Nama wajib diisi
* Umur minimal 1

## ✅ Global Exception Handler

Menampilkan error JSON rapi:

* Validation Error
* User Not Found

## ✅ DTO Pattern

* UserRequest
* ApiResponse<T>

## ✅ Professional API Response

Menggunakan:

```text
ResponseEntity
ApiResponse<T>
```

## ✅ Pagination + Sorting + Search

Contoh:

```text
GET /users?page=0&size=5
GET /users?sortBy=nama&direction=asc
GET /users/search?keyword=sam
```

## ✅ Swagger Documentation

Interactive API documentation:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 📁 Project Structure

```text
src/main/java/com/sammy/belajar_spring/

├── controller
│   └── UserController.java

├── service
│   └── UserService.java

├── repository
│   └── UserRepository.java

├── entity
│   └── User.java

├── dto
│   ├── UserRequest.java
│   └── ApiResponse.java

├── exception
│   ├── GlobalExceptionHandler.java
│   └── UserNotFoundException.java
```

---

# 🔥 API Endpoints

## User CRUD

```http
GET    /users
GET    /users/{id}
POST   /users
PUT    /users/{id}
DELETE /users/{id}
```

## Pagination

```http
GET /users?page=0&size=5
```

## Sorting

```http
GET /users?sortBy=nama&direction=asc
```

## Search

```http
GET /users/search?keyword=sam
```

---

# ⚙️ Cara Menjalankan Project

## 1. Clone Repository

```bash
git clone <repository-url>
```

## 2. Masuk Folder Project

```bash
cd belajar-spring
```

## 3. Setup Database

```sql
CREATE DATABASE belajar_spring;
```

## 4. Atur application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/belajar_spring
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 5. Jalankan Project

```bash
mvn spring-boot:run
```

---

# 🧪 Swagger Testing

Setelah project jalan:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🎯 Progress Belajar Saat Ini

```text
Spring Boot Basic        ✅
REST API CRUD           ✅
MySQL + JPA             ✅
Validation              ✅
Exception Handling      ✅
DTO Pattern             ✅
ResponseEntity          ✅
Pagination              ✅
Sorting                 ✅
Search                  ✅
Swagger OpenAPI         ✅
Git Workflow            ✅
```

---

# 🚀 Next Roadmap

* Spring Security
* JWT Authentication
* Unit Testing
* Docker
* Deploy VPS / Cloud
* Redis Cache
* CI/CD Pipeline
* Microservices
* AWS Deployment

---

# 👨‍💻 Author

Made with learning spirit using Java Spring Boot.
