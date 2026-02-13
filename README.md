# 🏗️ Microservices Task Scheduling Platform

A production-style **Spring Boot Microservices Architecture** demonstrating secure authentication, BFF orchestration, task scheduling validation, automated cron processing, and email notifications using Thymeleaf.

---

## 📌 Architecture Overview

This system follows a microservices architecture with a Backend-for-Frontend (BFF) pattern.


---

# 🚀 Microservices

---

## 1️⃣ bff-schedulingtask-api

**Backend For Frontend (BFF)** responsible for:

- Aggregating microservice responses
- Routing client requests
- Forwarding JWT tokens
- Handling orchestration logic
- Exposing simplified APIs to frontend

### Features
- Centralized entry point
- Secure token propagation
- Service-to-service communication
- Cron-based background execution

### Example Cron Job

```java
@Scheduled(cron = "0 0/5 * * * ?")
public void processPendingSchedules() {
    // Trigger task processing every 5 minutes
}
```

---

## 2️⃣ registration-api

Handles **authentication & authorization** using **Spring Security + JWT**.

### Features

- User registration
- Secure login
- Password encryption (BCrypt)
- JWT token generation
- Role-based authorization

### Authentication Flow

1. User logs in
2. JWT token is issued
3. Client sends the token in the request header:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 3️⃣ scheduling-api

Core business service responsible for **validating and scheduling tasks**.

### Responsibilities

- Validate customer existence
- Check customer status (active/inactive)
- Validate scheduling time conflicts
- Prevent duplicate scheduling
- Persist scheduling data

### Validation Rules Example

- Customer must exist
- Customer must be active
- Task date cannot be in the past
- Time slot must be available

---

## 4️⃣ notification-api

Handles **email notifications** using **Thymeleaf templates**.

### Features

- HTML email templates
- Dynamic template variables
- SMTP integration
- Triggered after successful scheduling
- Supports event-based notifications

### Example Email Template

```html
<h1>Hello [[${customerName}]]</h1>
<p>Your task is scheduled for [[${scheduledTime}]]</p>
```
---

## 🐳 Docker & Deployment

### Build Images

```bash
docker build -t registration-api ./registration-api
docker build -t scheduling-api ./scheduling-api
docker build -t notification-api ./notification-api
docker build -t bff-api ./bff-schedulingtask-api

### Run Containers
docker run -p 8081:8081 registration-api
docker run -p 8082:8082 scheduling-api
docker run -p 8083:8083 notification-api
docker run -p 8080:8080 bff-api
```
---
