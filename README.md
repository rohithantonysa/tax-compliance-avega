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

---

## ⚙️ Setup & Installation

### 1. Database Configuration
1. Create a MySQL schema named `tax_compliance_db`.
2. Open `src/main/resources/application.properties` and update the datasource credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tax_compliance_db
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update