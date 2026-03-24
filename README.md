# Tax Compliance & Validation Engine

A robust Spring Boot 3.x backend application designed to automate the ingestion, validation, and reporting of financial transactions for tax compliance.

## 🚀 Key Features
- **Batch Transaction Ingestion:** High-performance REST API for processing transaction batches.
- **Dynamic Rule Engine:** Database-driven compliance checks (e.g., High-Value Transaction flags) that can be updated at runtime.
- **Automated Tax Calculation:** Real-time computation of "Tax Gaps" (Expected vs. Reported Tax).
- **Role-Based Security:** Protected endpoints using Spring Security (Basic Auth) with BCrypt password hashing.
- **Audit Logging:** Detailed exception records for rule violations to support forensic auditing.

## 🛠️ Tech Stack
- **Java:** version 17
- **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA, Spring Security)
- **Database:** MySQL 8.0
- **Build Tool:** Maven
- **Testing:** JUnit 5, Mockito, JaCoCo (Coverage)

## ⚙️ Setup & Installation





### 1. Database Configuration
1. Create a MySQL schema named `tax_compliance_db`.
2. Open `src/main/resources/application.properties` and update the datasource credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tax_compliance_db
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update


Build and Run
1.	From the root directory, run:
mvn clean install
2.	Start the application:
mvn spring-boot:run
Note: On first run, a default admin user is created: admin / password123.
📊 API Documentation & Sample Calls
Authentication
All endpoints require Basic Auth.
•	Username: admin
•	Password: password123






1. Upload Transactions (POST)
Endpoint: /api/transactions/upload
Description: Processes a batch of transactions and runs compliance rules.
Sample Payload:
JSON
[
  {
    "transactionId": "TXN-AUTO-001",
    "customerId": "CUST-101",
    "amount": 65000.00,
    "taxRate": 0.18,
    "reportedTax": 11000.00,
    "transactionType": "SALE",
    "date": "2026-03-24"
  }
]

2. Compliance Summary Report (GET)
Endpoint: /api/reports/summary
Description: Returns a calculated tax gap summary per customer.
________________________________________
🏗️ Design Decisions
•	DTO Pattern: Used TransactionDTO to decouple the API contract from the Database Schema.
•	Strategy Pattern: Rule engine implementation allows adding new tax laws via DB configuration without code changes.
•	BCrypt: Passwords are never stored in plain text to ensure security compliance.

