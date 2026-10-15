-- Student Registration System - SQLite schema

CREATE TABLE PERSON (
    person_id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    phone_number TEXT
);

CREATE TABLE STUDENT (
    student_id TEXT PRIMARY KEY,
    major TEXT,
    password TEXT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES PERSON(person_id)
);

CREATE TABLE ADVISOR (
    advisor_id TEXT PRIMARY KEY,
    password TEXT NOT NULL,
    FOREIGN KEY (advisor_id) REFERENCES PERSON(person_id)
);

CREATE TABLE COURSE (
    course_id TEXT PRIMARY KEY,
    course_name TEXT NOT NULL,
    credit_hours INTEGER NOT NULL
);

CREATE TABLE REGISTRATION (
    reg_id INTEGER PRIMARY KEY AUTOINCREMENT,
    student_id TEXT NOT NULL,
    course_id TEXT NOT NULL,
    status TEXT NOT NULL DEFAULT 'registered',
    FOREIGN KEY (student_id) REFERENCES STUDENT(student_id),
    FOREIGN KEY (course_id) REFERENCES COURSE(course_id)
);

CREATE TABLE Login (
    id TEXT PRIMARY KEY,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);
