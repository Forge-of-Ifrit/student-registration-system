# Student Registration System

A Java desktop application for managing course registration at a college. Students log in to browse courses and request registration, and advisors review the requests and approve or drop them. Built with **Java Swing**, **SQLite**, and **JDBC**, using a layered **DAO** architecture and the **Singleton** design pattern.

Built by a team of 5 for *Advanced Object-Oriented Programming (CIA 3503)* at the Higher Colleges of Technology (HCT), Abu Dhabi.

![Java](https://img.shields.io/badge/Java-21-red?logo=openjdk) ![Swing](https://img.shields.io/badge/GUI-Swing-blue) ![SQLite](https://img.shields.io/badge/DB-SQLite-003B57?logo=sqlite) ![Maven](https://img.shields.io/badge/build-Maven-C71A36?logo=apachemaven)

## Features

- **Role-based login**: one login screen sends users to the Student or Advisor dashboard based on their role in the database.
- **Student dashboard**: view all available courses (ID, name, credit hours) and request registration for a selected course.
- **Advisor dashboard**: view all registration requests, select one, and mark it **Approved** or **Dropped**.
- **Full CRUD layer**: separate DAO classes for students, advisors, courses, and registrations, all using parameterized `PreparedStatement` queries to block SQL injection.

## OOP concepts demonstrated

| Concept | Where |
|---------|-------|
| Abstraction & inheritance | `Person` (abstract) → `Student`, `Advisor` |
| Polymorphism | `showInfo()` / `validate()` overridden in each subclass |
| Encapsulation | Private fields with getters and setters in every model |
| DAO pattern | `StudentDAO`, `AdvisorDAO`, `CourseDAO`, `RegistrationDAO`, `LoginDAO` |
| Singleton pattern | `RegistrationDAO.getInstance()`, checked by `SingletonTest` |

## Database

Relational SQLite schema with primary and foreign keys:

```
PERSON (person_id PK, name, email, phone_number)
 ├── STUDENT (student_id PK/FK, major, password)
 └── ADVISOR (advisor_id PK/FK, password)
COURSE (course_id PK, course_name, credit_hours)
REGISTRATION (reg_id PK, student_id FK, course_id FK, status)
Login (id PK, password, role)
```

The scripts are in [`database/`](database/). `StudentReq.db` comes pre-loaded with 20 sample courses and demo accounts.

## Getting started

Requirements: **JDK 21** and **Maven** (or open the folder in NetBeans or IntelliJ as a Maven project).

```bash
git clone https://github.com/Forge-of-Ifrit/student-registration-system.git
cd student-registration-system
mvn compile exec:java
```

Run it from the project root so the app can find `StudentReq.db`.

**Demo accounts**

| Role | ID | Password |
|------|----|----------|
| Student | `H001` | `1234` |
| Advisor | `A001` | `1234` |

## Project structure

```
src/main/java/com/mycompany/studentregestrationsystem/
├── Person, Student, Advisor, Course, Registration   # models
├── *DAO.java, DBConnection.java                     # data access layer
├── Login, StudentGUI, AdvisorGUI (.java + .form)    # Swing UI (NetBeans GUI Builder)
└── SingletonTest.java                               # Singleton pattern check
```

## Possible improvements

- Hash passwords (for example with BCrypt) instead of storing plain text
- Stop students from registering for the same course twice
- Add an admin screen for managing courses and users
