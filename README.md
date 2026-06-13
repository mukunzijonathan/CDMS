# 🚗 Car Dealership Management System

A RESTful API (academic project)built with Spring Boot for managing a car dealership — covering cars, customers, employees, sales, and a full Rwanda administrative location hierarchy.

![Java](https://img.shields.io/badge/Java-21-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.3-brightgreen?logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-blue?logo=postgresql)
![Maven](https://img.shields.io/badge/Build-Maven-red?logo=apachemaven)

---

## ERD

<img src="screenshots/ERD.png" width="700"/>

---

## Tech Stack

| Layer     | Technology                  |
|-----------|-----------------------------|
| Framework | Spring Boot 4.0.3           |
| Language  | Java 21                     |
| Database  | PostgreSQL                  |
| ORM       | Spring Data JPA / Hibernate |
| Docs      | Springdoc OpenAPI (Swagger) |
| Build     | Maven                       |

---

## Getting Started

### Prerequisites
- Java 21+
- PostgreSQL running on port `5432`
- Maven

### 1. Create the database
```sql
CREATE DATABASE "car-dealership";
```

### 2. Configure credentials
Copy `application.properties.example` → `application.properties` and fill in your values:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/car-dealership
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### 3. Run
```bash
mvn spring-boot:run
```
API available at `http://localhost:8080`  
Swagger UI at `http://localhost:8080/swagger-ui/index.html`

---

## API Endpoints

### Location
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/locations/add` | Add a location |
| GET | `/api/locations/all` | Get all locations |
| GET | `/api/locations/provinces` | Get all provinces |
| GET | `/api/locations/children?parentId=` | Get children of a location |
| GET | `/api/locations/getById?id=` | Get location by UUID |

### Customer
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers/add` | Add customer |
| GET | `/api/customers/all` | Get all customers |
| GET | `/api/customers/getById?id=` | Get by UUID |
| GET | `/api/customers/province?name=` | Filter by province name |
| GET | `/api/customers/provinceId?id=` | Filter by province UUID |
| PUT | `/api/customers/update?id=` | Update customer |
| DELETE | `/api/customers/delete?id=` | Delete customer |

### Car
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/cars/add` | Add car |
| GET | `/api/cars/all` | Get all cars |
| GET | `/api/cars/paginated?page=0&size=5` | Paginated list |
| GET | `/api/cars/sorted/price` | Sort by price (ASC) |
| GET | `/api/cars/sorted/year` | Sort by year (DESC) |

### Employee
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/employees/add` | Add employee |
| GET | `/api/employees/all` | Get all employees |
| GET | `/api/employees/province?name=` | Filter by province name |
| GET | `/api/employees/provinceId?id=` | Filter by province UUID |

### Sale
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/sales/add` | Create sale (links multiple cars) |
| GET | `/api/sales/all` | Get all sales |
| GET | `/api/sales/getById?id=` | Get sale with linked cars |

---

## Project Structure

```
src/main/java/com/example/CarDealership/
├── model/        # JPA entities (Location, Customer, Car, Employee, Sale)
├── repository/   # Spring Data repositories with custom @Query methods
├── service/      # Business logic layer
└── controller/   # REST controllers
```

---

## Key Features

- **Self-referencing location tree** — Province → District → Sector → Cell → Village
- **Province-level queries** — JPQL traverses up to 4 parent levels
- **Pagination & sorting** — on Car listings
- **Duplicate prevention** — `existsByEmail` check on Customer
- **Many-to-Many sales** — Sale ↔ Car via `sale_car` join table
