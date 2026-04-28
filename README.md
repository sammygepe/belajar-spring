# Belajar Spring Boot API 🚀

Project backend REST API menggunakan **Java 17 + Spring Boot + Spring Data JPA + MySQL**.  
Dibuat sebagai media belajar serius menuju **Backend Developer Java Spring Boot** dengan fitur real-world seperti transaksi order, dashboard, pagination, dan analytics sederhana.

---

# 🚀 Tech Stack

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Swagger OpenAPI
- IntelliJ IDEA
- Postman
- Git

---

# 📦 Features

# 👤 User Module

## ✅ CRUD User

- Create User
- Get All Users
- Get User By ID
- Update User
- Delete User

## ✅ Validation

Menggunakan Bean Validation:

- Nama wajib diisi
- Umur minimal 1

---

# 🧾 Order Module (Header - Detail)

Simulasi transaksi invoice sederhana.

## Order Header

- Invoice No
- Order Date
- User
- Grand Total

## Order Detail

- Item Name
- Qty
- Price
- Subtotal

## ✅ Fitur Order

- Create Order banyak item
- Auto hitung subtotal
- Auto hitung grand total
- Get All Orders
- Get Order By ID
- Delete Order
- Search Invoice

---

# 📄 Pagination + Sorting

✅ Order Pagination
GET /orders/page?page=0&size=5&sort=id,desc

✅ Search + Pagination
GET /orders/search/page?invoice=INV&page=0&size=5

📊 Dashboard Summary
Menampilkan ringkasan bisnis sederhana.

✅ Endpoint
GET /orders/dashboard
Response
{
  "message": "Success dashboard",
  "data": {
    "totalOrders": 3,
    "totalRevenue": 1500000
  }
}


🏆 Top Customer
Menampilkan customer dengan transaksi terbesar.

Endpoint
GET /orders/top-customers
Response
{
  "message": "Success get top customers",
  "data": [
    {
      "customerName": "Sammy",
      "totalOrders": 2,
      "totalAmount": 2500000
    }
  ]
}


🛡️ Global Exception Handler
Menampilkan response error rapi.

Contoh Validation Error
{
  "message": "Validation failed",
  "data": {
    "nama": "Nama wajib diisi"
  }
}
Contoh Not Found
{
  "message": "User id 99 tidak ditemukan",
  "data": null
}


📁 Project Structure
src/main/java/com/sammy/belajar_spring/

├── controller
│   ├── UserController.java
│   └── OrderController.java

├── service
│   ├── UserService.java
│   └── OrderService.java

├── repository
│   ├── UserRepository.java
│   ├── OrderHeaderRepository.java
│   └── OrderDetailRepository.java

├── entity
│   ├── User.java
│   ├── OrderHeader.java
│   └── OrderDetail.java

├── dto
│   ├── ApiResponse.java
│   ├── CreateOrderRequest.java
│   ├── CreateOrderDetailRequest.java
│   ├── DashboardResponse.java
│   ├── TopCustomerResponse.java
│   └── PageResponse.java

├── exception
│   ├── GlobalExceptionHandler.java
│   └── UserNotFoundException.java


🔥 API Endpoints

User
GET    /users
GET    /users/{id}
POST   /users
PUT    /users/{id}
DELETE /users/{id}

Order
GET    /orders
GET    /orders/{id}
POST   /orders
DELETE /orders/{id}
Search
GET /orders/search?invoice=INV001
Pagination
GET /orders/page?page=0&size=5
GET /orders/page?page=0&size=5&sort=id,desc
Dashboard
GET /orders/dashboard
Top Customer
GET /orders/top-customers


⚙️ Cara Menjalankan Project
1. Clone Repository
git clone <repository-url>
2. Masuk Folder Project
cd belajar-spring
3. Setup Database
CREATE DATABASE belajar_spring;
4. Atur application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/belajar_spring
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
5. Jalankan Project
mvn spring-boot:run


🧪 Swagger Testing
Setelah project jalan:
http://localhost:8080/swagger-ui/index.html


🎯 Progress Belajar Saat Ini
Spring Boot Basic       ✅
REST API CRUD           ✅
MySQL + JPA             ✅
Validation              ✅
Exception Handling      ✅
DTO Pattern             ✅
ResponseEntity          ✅
Order Header Detail     ✅
Pagination              ✅
Sorting                 ✅
Search                  ✅
Dashboard Summary       ✅
Top Customer            ✅
Swagger OpenAPI         ✅
Git Workflow            ✅


🚀 Next Roadmap
JWT Authentication
Role USER / ADMIN
Product Management
Stock Management
Unit Testing
Docker
Deploy VPS / Cloud
Redis Cache
CI/CD Pipeline
Microservices
AWS Deployment


👨‍💻 Author
Made with learning spirit using Java Spring Boot 🚀