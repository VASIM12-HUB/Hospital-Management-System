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

<img width="1915" height="994" alt="image" src="https://github.com/user-attachments/assets/e2efe84f-3a59-43e1-b20f-b5e2d00c0235" />


### 📝 Register

<img width="1916" height="993" alt="image" src="https://github.com/user-attachments/assets/b5922a91-fb64-4f20-90b7-a9f323df1f17" />


### 📊 Dashboard

<img width="1919" height="1006" alt="image" src="https://github.com/user-attachments/assets/8a873192-cfec-4d12-85af-4eaa0ffc1bfd" />


### 🧑 Patients

<img width="1882" height="1023" alt="image" src="https://github.com/user-attachments/assets/f1285c04-aa92-4ec1-abb9-cd866bb649fc" />


### 🧑‍⚕️ Doctors

<img width="1915" height="1001" alt="image" src="https://github.com/user-attachments/assets/9d464233-595c-4469-9728-d67e79b3f30b" />


### 📅 Appointments

<img width="1888" height="996" alt="image" src="https://github.com/user-attachments/assets/f205a4e1-1acb-4b8a-87ee-c5fa1f75734a" />


### 🗂️ Medical Records

<img width="1901" height="1005" alt="image" src="https://github.com/user-attachments/assets/d85fc1a8-b748-40f4-a6b0-13cc3e838db7" />




---
