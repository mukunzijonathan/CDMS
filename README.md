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
| Validation| Jakarta Bean Validation     |
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
CREATE DATABASE "your-db-name";
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
mvn clean install
mvn spring-boot:run
```
API available at `http://localhost:"your-port"`  
Swagger UI at `http://localhost:8080/swagger-ui/index.html`

---

## API Endpoints

Request bodies are validated DTOs; responses are DTOs (entities are never exposed
directly). Relationships are set by id — e.g. a customer references a location via
`locationId`, a sale references `customerId`, `employeeId` and a list of `carIds`.

### Location
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/locations/add` | Add a location (`{name, type, parentId?}`) |
| GET | `/api/locations/all` | Get all locations |
| GET | `/api/locations/provinces` | Get all provinces |
| GET | `/api/locations/children?parentId=` | Get children of a location |
| GET | `/api/locations/search?name=` | Find locations by name |
| GET | `/api/locations/getById?id=` | Get location by UUID |
| PUT | `/api/locations/update?id=` | Update location |
| DELETE | `/api/locations/delete?id=` | Delete location (204) |

### Customer
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers/add` | Add customer (`{firstName, lastName, email, phoneNumber, locationId}`) |
| GET | `/api/customers/all` | Get all customers |
| GET | `/api/customers/getById?id=` | Get by UUID |
| GET | `/api/customers/location?name=` | Filter by exact location name |
| GET | `/api/customers/province?name=` | Filter by province name |
| GET | `/api/customers/provinceId?id=` | Filter by province UUID |
| PUT | `/api/customers/update?id=` | Update customer |
| DELETE | `/api/customers/delete?id=` | Delete customer (204) |

### Car
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/cars/add` | Add car (`{brand, model, year, price}`) |
| GET | `/api/cars/all` | Get all cars |
| GET | `/api/cars/paginated?page=0&size=5` | Paginated list |
| GET | `/api/cars/sorted/price` | Sort by price (ASC) |
| GET | `/api/cars/sorted/year` | Sort by year (DESC) |
| GET | `/api/cars/getById?id=` | Get car by id |
| GET | `/api/cars/brand?brand=` | Filter by brand |
| PUT | `/api/cars/update?id=` | Update car |
| DELETE | `/api/cars/delete?id=` | Delete car (204) |

### Employee
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/employees/add` | Add employee (`{firstName, lastName, email, locationId}`) |
| GET | `/api/employees/all` | Get all employees |
| GET | `/api/employees/getById?id=` | Get by id |
| GET | `/api/employees/location?name=` | Filter by exact location name |
| GET | `/api/employees/province?name=` | Filter by province name |
| GET | `/api/employees/provinceId?id=` | Filter by province UUID |
| PUT | `/api/employees/update?id=` | Update employee |
| DELETE | `/api/employees/delete?id=` | Delete employee (204) |

### Sale
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/sales/add` | Create sale (`{saleDate, finalPrice, paymentMethod, customerId, employeeId, carIds[]}`) |
| GET | `/api/sales/all` | Get all sales |
| GET | `/api/sales/getById?id=` | Get sale with linked cars |
| GET | `/api/sales/customer?customerId=` | Sales for a customer |
| GET | `/api/sales/employee?employeeId=` | Sales for an employee |
| GET | `/api/sales/payment?paymentMethod=` | Sales by payment method (`CASH`/`CARD`) |
| PUT | `/api/sales/update?id=` | Update sale |
| DELETE | `/api/sales/delete?id=` | Delete sale (204) |

---

## Error Handling

A global `@RestControllerAdvice` maps failures to consistent JSON with correct
status codes:

| Situation | Status |
|-----------|--------|
| Resource not found | `404 Not Found` |
| Duplicate (email / name+type / brand+model+year) | `409 Conflict` |
| Bean-validation failure | `400 Bad Request` (with per-field messages) |
| Malformed body / bad param / unknown enum | `400 Bad Request` |
| FK constraint violation (e.g. deleting a referenced record) | `409 Conflict` |

Error body shape:
```json
{
  "timestamp": "2026-07-03T10:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "fieldErrors": { "email": "email must be a valid address" }
}
```

---

## Project Structure

```
src/main/java/com/example/CarDealership/
├── model/        # JPA entities (Location, Customer, Car, Employee, Sale)
├── dto/          # Request/response DTOs (validated input, mapped output)
├── repository/   # Spring Data repositories with custom @Query methods
├── service/      # Business logic — validation, mapping, exceptions
├── controller/   # Thin REST controllers
└── exception/    # Custom exceptions + global handler
```

---

## Key Features

- **Self-referencing location tree** — Province → District → Sector → Cell → Village
- **Province-level queries** — JPQL traverses up to 4 parent levels
- **Pagination & sorting** — on Car listings
- **DTO boundary** — entities never leave the service layer; input is validated
- **Duplicate prevention** — `existsBy…` guards on Customer, Employee, Car, Location
- **`BigDecimal` money** — `Car.price` and `Sale.finalPrice` avoid floating-point drift
- **Many-to-Many sales** — Sale ↔ Car via `sale_car` join table, linked by `carIds`
