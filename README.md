# 🎓 Educational Institution Backend – Technical Overview

### 🔧 Tech Stack
- **Language**: Java 17  
- **Framework**: Spring Boot  
- **Database**: MongoDB  
- **Build Tool**: Maven  
- **IDE Recommended**: IntelliJ IDEA

---

## ✅ Project Purpose

This backend service is part of a platform designed to manage educational institutions and their branches (headquarters). It provides REST APIs to support academic and administrative operations such as institution registration, branch management, and data access for external systems or frontend applications.

---

## 🛠️ Setup Instructions

You **must** have the following installed:
- Java 17
- Maven
- MongoDB running locally or in the cloud

### 1️⃣ Clone the repository

```bash
git clone https://github.com/YourOrg/educational-platform.git
cd educational-platform/backend
```

### 2️⃣ Run the backend server

```bash
./mvnw spring-boot:run
```

The application will start at:  
[http://localhost:8080](http://localhost:8080)

---

## 🔐 Environment Variables

You **must** configure the following environment variables (via `.env`, system, or `application.properties`):

```properties
MONGODB_URI=mongodb://localhost:27017/edu-platform
JWT_SECRET=your_jwt_secret_key
```

---

## 📁 Project Structure

```
/backend
├── src/
│   └── main/
│       ├── java/com/yourorg/admisys/
│       │   ├── application/services/
│       │   │   ├── InstitutionService.java
│       │   │   └── InstitutionServiceImpl.java
│       │   ├── domain/models/
│       │   │   ├── Institution.java
│       │   │   └── Headquarter.java
│       │   ├── infrastructure/config/
│       │   │   └── CorsConfig.java
│       │   ├── infrastructure/handlers/
│       │   │   └── InstitutionHandler.java
│       │   ├── infrastructure/repositories/
│       │   │   ├── InstitutionRepository.java
│       │   │   └── HeadquarterRepository.java
│       │   └── AdmisysApplication.java
│       └── resources/
│           └── application.properties
├── pom.xml
└── README.md
```

---

## 📦 API Overview

| Method | Endpoint                    | Description                          |
|--------|-----------------------------|--------------------------------------|
| GET    | `/institutions`             | List all institutions                |
| GET    | `/institutions/{id}`        | Get a specific institution           |
| POST   | `/institutions`             | Create a new institution             |
| PUT    | `/institutions/{id}`        | Update an existing institution       |
| DELETE | `/institutions/{id}`        | Delete an institution                |

> You **should** use Postman or curl to test the endpoints locally.

---

## ✅ Best Practices

- You **should** follow domain-driven design (DDD) structure.
- You **should** document new endpoints using Swagger or Postman collections.
- You **should** write unit and integration tests using JUnit and Mockito.
- You **must** run:
  ```bash
  mvn clean install
  ```
  before pushing changes.

---

## 🚀 Deployment

You **must** build the project before deployment:
```bash
./mvnw clean package
```

You **need to** configure your MongoDB URI and environment secrets in your hosting environment.

---

## 🧑‍💻 Contributing

1. Fork the repository  
2. Create a new branch:  
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Make your changes and commit  
4. Push and open a Pull Request  
5. You **should** add `Fixes #<issue-number>` in the PR if solving a known issue

---

## 📞 Support

- Open an issue in this repository.  
- Tag **@project-lead** for urgent support.  
- Join our dev chat on **Discord** or **Telegram** for collaboration.

---

**Thanks for your contribution!**  
👍 Let’s build reliable educational software together.
