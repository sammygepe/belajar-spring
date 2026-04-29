# Belajar Spring Boot REST API

Project latihan backend menggunakan **Java Spring Boot**, **MySQL**, dan **Spring Data JPA**.  
Project ini berkembang dari CRUD dasar menjadi backend dengan fitur authentication, dashboard, pagination, dan JWT login.

---

# 🚀 Tech Stack

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Security (BCrypt)
- JWT (JJWT)
- MySQL
- Maven

---

# 📦 Fitur Saat Ini

## ✅ User

- Register user
- Login user
- Password di-hash BCrypt
- JWT token saat login

## ✅ Order

- Create Order
- Multi Item Order Detail
- Get All Orders
- Get Order By ID
- Delete Order
- Search Invoice

## ✅ Pagination

- List order pagination
- Search invoice + pagination

## ✅ Dashboard

- Total Orders
- Total Revenue
- Average Order
- Top Customers

## ✅ Global Handling

- Validation error response
- Custom exception response
- Clean JSON API response

---

# 📁 Struktur Project

src/main/java/com/sammy/belajar_spring
├── controller
├── service
├── repository
├── entity
├── dto
├── config
└── exception


🔐 Authentication

Register
POST /auth/register

Request
{
  "nama": "Sammy",
  "umur": 25,
  "username": "sammy",
  "password": "123456"
}

Login
POST /auth/login

Request
{
  "username": "sammy",
  "password": "123456"
}

Response
{
  "message": "Login success",
  "data": "jwt_token_here"
}


📦 Order API

Create Order
POST /orders
{
  "invoiceNo": "INV001",
  "orderDate": "2026-04-01",
  "userId": 1,
  "details": [
    {
      "itemName": "Laptop",
      "qty": 1,
      "price": 12000000
    },
    {
      "itemName": "Mouse",
      "qty": 2,
      "price": 150000
    }
  ]
}

Get Orders
GET /orders
Get Order By ID
GET /orders/{id}
Delete Order
DELETE /orders/{id}
Search Invoice
GET /orders/search?invoice=INV001

📄 Pagination
Orders Pagination
GET /orders/page?page=0&size=5
Search + Pagination
GET /orders/search/page?invoice=INV&page=0&size=5

📊 Dashboard
Summary
GET /dashboard/summary

Response:
{
  "totalOrders": 10,
  "totalRevenue": 25000000,
  "averageOrder": 2500000
}

Top Customers
GET /dashboard/top-customers


🧱 JSON Response Format
{
  "message": "Success",
  "data": {}
}


🔐 Password Security
Password user disimpan menggunakan:
BCryptPasswordEncoder


🧪 Swagger
http://localhost:8080/swagger-ui/index.html


👨‍💻 Author
Learning project by Sammy 🚀