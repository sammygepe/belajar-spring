# BELAJAR SPRING BOOT PROJECT

Nama Project:
belajar-spring

Deskripsi:
Project latihan backend menggunakan Java Spring Boot + MySQL dengan pola Clean Architecture sederhana.

# STRUKTUR PACKAGE

com.sammy.belajar_spring
│
├── BelajarSpringApplication.java
│   -> Main class untuk menjalankan aplikasi
│
├── controller/
│   └── UserController.java
│   -> Menerima HTTP request dan expose REST API
│
├── service/
│   └── UserService.java
│   -> Business logic aplikasi
│
├── repository/
│   └── UserRepository.java
│   -> Akses database menggunakan Spring Data JPA
│
├── entity/
│   └── User.java
│   -> Mapping tabel users di MySQL
│
├── dto/
│   └── UserRequest.java
│   -> Request body + validation input
│
└── exception/
├── GlobalExceptionHandler.java
│   -> Menangani error menjadi JSON response
└── UserNotFoundException.java
-> Error custom jika data user tidak ditemukan

# FITUR SELESAI

[✓] Setup Spring Boot Maven project
[✓] Koneksi MySQL
[✓] Konfigurasi application.properties
[✓] Spring Data JPA + Hibernate
[✓] Auto create table users
[✓] Full CRUD API
[✓] Layer Controller / Service / Repository
[✓] Validation input (@NotBlank, @Min)
[✓] Error JSON response
[✓] Global Exception Handler
[✓] Clean package structure
[✓] Git ready project

# API ENDPOINT

GET    /users
GET    /users/{id}
POST   /users
PUT    /users/{id}
DELETE /users/{id}

# CONTOH REQUEST POST

{
"nama": "Sammy",
"umur": 25
}

# CONTOH ERROR VALIDATION

{
"nama": "Nama wajib diisi",
"umur": "Umur minimal 1"
}

# ROADMAP NEXT LEVEL

[ ] ResponseEntity standard response
[ ] Pagination
[ ] Search & Filter
[ ] Swagger OpenAPI
[ ] Spring Security + JWT
[ ] Unit Testing JUnit Mockito
[ ] Docker
[ ] CI/CD
[ ] Microservices
[ ] AWS Deployment

# CONTOH COMMIT GIT

feat: setup spring boot project
feat: connect mysql using jpa
feat: create full crud users api
feat: add validation and global exception handler
refactor: apply clean architecture package structure

docs: update readme project progress
