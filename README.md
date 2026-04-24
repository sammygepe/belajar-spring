# BELAJAR SPRING BOOT PROJECT

Nama Project:
belajar-spring

Deskripsi:
Project latihan backend menggunakan Java Spring Boot + MySQL dengan struktur clean architecture sederhana.
Saat ini project sudah berkembang menjadi REST API professional level basic-intermediate.

# STRUKTUR PACKAGE

com.sammy.belajar_spring
│
├── BelajarSpringApplication.java
│   -> Main class untuk menjalankan aplikasi
│
├── controller/
│   └── UserController.java
│   -> Menerima HTTP request dan expose REST API endpoint
│
├── service/
│   └── UserService.java
│   -> Menangani business logic aplikasi
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
│   ├── UserRequest.java
│   -> Request body + validation input
│   │
│   └── ApiResponse.java
│   -> Standard response JSON API
│
└── exception/
├── GlobalExceptionHandler.java
│   -> Menangani error menjadi JSON response
│
└── UserNotFoundException.java
-> Custom error jika user tidak ditemukan

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
[✓] ResponseEntity standard API response
[✓] ApiResponse<T> generic response
[✓] Pagination
[✓] Sorting
[✓] Search user by nama
[✓] Git ready project

# API ENDPOINT

GET    /users
GET    /users/{id}
POST   /users
PUT    /users/{id}
DELETE /users/{id}

GET    /users?page=0&size=5
GET    /users?sortBy=nama&direction=asc
GET    /users/search?keyword=sam
GET    /users/search?keyword=sam&page=0&size=5

# CONTOH REQUEST POST

{
"nama": "Sammy",
"umur": 25
}

# CONTOH SUCCESS RESPONSE

{
"message": "User created",
"data": {
"id": 1,
"nama": "Sammy",
"umur": 25
}
}

# CONTOH ERROR VALIDATION

{
"nama": "Nama wajib diisi",
"umur": "Umur minimal 1"
}

# CONTOH ERROR NOT FOUND

{
"error": "User dengan id 99 tidak ditemukan"
}

# PROGRESS LEVEL

Spring Boot Basic        [✓]
REST API CRUD           [✓]
MySQL + JPA             [✓]
Validation              [✓]
Exception Handling      [✓]
DTO Pattern             [✓]
ResponseEntity          [✓]
Pagination              [✓]
Sorting                 [✓]
Search                  [✓]

# ROADMAP NEXT LEVEL

[ ] Swagger OpenAPI
[ ] Spring Security + JWT
[ ] Unit Testing JUnit Mockito
[ ] Docker
[ ] Deploy VPS / Cloud
[ ] Redis Cache
[ ] CI/CD Pipeline
[ ] Microservices
[ ] AWS Deployment
[ ] System Design

# CONTOH COMMIT GIT

feat: setup spring boot project
feat: connect mysql using jpa
feat: create full crud users api
feat: add validation and global exception handler
feat: add standard api response with ResponseEntity
feat: add pagination sorting and search for user api
refactor: apply clean architecture package structure

docs: update readme project progress