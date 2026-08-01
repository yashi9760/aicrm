# 🏢 AI-Powered CRM Backend Platform

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Gemini AI](https://img.shields.io/badge/Google%20Gemini-AI%20Integration-8E75B2?style=for-the-badge&logo=googlegemini&logoColor=white)
![Build](https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

An enterprise-ready **Customer Relationship Management (CRM)** RESTful API backend engineered with **Spring Boot 3** and integrated with **Google Gemini AI**. Designed to streamline business workflows, automate intelligent customer communication, and deliver real-time data insights securely.

---

## 🎯 Key Capabilities

* 🤖 **Generative AI Workflows**: Direct integration with Google's Gemini API for automated lead response generation, sentiment analysis, and interaction summarization.
* 🔒 **Granular Authorization**: Secured via **Spring Security** with Stateless **JWT Tokens** and Role-Based Access Control (`ROLE_USER`, `ROLE_ADMIN`).
* ⚡ **High-Performance Architecture**: Structured on clean **Layered Architecture** (Controller, Service, Repository, DTO/Model pattern).
* 🗄️ **Persistence**: Uses **Spring Data JPA / Hibernate** with an in-memory **H2 Database** for zero-setup local dev/testing; easily swappable for PostgreSQL/MySQL in production.
* 🛠️ **DevOps & CI/CD Ready**: Configured with Maven wrapper (`mvnw`) for deterministic builds across environments.

---

## 🏗️ Architecture & Project Structure

```text
aicrm/
├── src/
│   ├── main/
│   │   ├── java/jar/
│   │   │   ├── controller/     # REST Endpoints (Auth, Customer, AI Controller)
│   │   │   ├── service/        # Business Logic & Gemini API Orchestration
│   │   │   ├── repository/     # Spring Data JPA Data Access Layer
│   │   │   ├── model/          # JPA Entities (User, Role, Customer)
│   │   │   ├── jwt/            # Security Filters & Token Utilities
│   │   │   └── security/       # Web Security Configuration
│   │   └── resources/
│   │       └── application.properties # Environment Configuration
│   └── test/                   # JUnit 5 & Mockito Test Suites
└── pom.xml                     # Dependency Management
