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
### 🔐 Login

<img width="1913" height="992" alt="image" src="https://github.com/user-attachments/assets/9fb0207f-43b2-4476-a7ce-c449fae8b7d4" />

### 📊 Dashboard

<img width="1919" height="999" alt="image" src="https://github.com/user-attachments/assets/57fa67e1-9ec4-44b6-bda0-c6d210b75839" />

### 🧑 Patients

<img width="1891" height="999" alt="image" src="https://github.com/user-attachments/assets/c6230989-f340-4e10-a221-07f5f83f7e16" />

### 🧑‍⚕️ Doctors

<img width="1914" height="995" alt="image" src="https://github.com/user-attachments/assets/4e32fd22-6a6e-4c05-b758-9ed550d13656" />

### 📅 Appointments

<img width="1897" height="1002" alt="image" src="https://github.com/user-attachments/assets/6cd60b09-25d3-4bed-81e4-0ac86738f409" />

### 🗂️ Medical Records

<img width="1909" height="1001" alt="image" src="https://github.com/user-attachments/assets/4c7af66e-49f8-4511-82b2-f2b65ab7fc4b" />



---
