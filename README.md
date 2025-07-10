# 🏥 Hospital Management System

A secure and efficient Hospital Management System developed using **Spring Boot**. This backend application provides RESTful APIs to manage patients, doctors, appointments, and user roles. It ensures authenticated access with **JWT tokens** and integrates with a **MySQL** database using **Spring Data JPA**.

---

## ✅ Features

- JWT-based user authentication and role-based authorization
- CRUD operations for:
  - Patients
  - Doctors
  - Appointments
- RESTful API design using Spring Boot
- MySQL database integration via Spring Data JPA

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot (Core, REST, Data JPA)
- MySQL
- JWT (JSON Web Token)
- Maven

---

## 🚀 Setup & Installation

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/Healthcare.git
cd Healthcare
```

### 2. Configure MySQL

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospitaldb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
server.port=9090
```

### 3. Build & Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

Now visit: [http://localhost:9090](http://localhost:9090)

---

## 📸 Output Screenshot

> Add screenshots of the API response or UI

![Login](screenshots/sample-output.png)
![Dashboard](screenshots/sample-output.png)
![Patient](screenshots/sample-output.png)
![Doctor](screenshots/sample-output.png)
![Appointment](screenshots/sample-output.png)
![Medical Records](screenshots/sample-output.png)
---
