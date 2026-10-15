-- Sample data (demo accounts only)

INSERT INTO PERSON VALUES ('H001','Sample Student','student@example.com','0500000001');
INSERT INTO PERSON VALUES ('A001','Sample Advisor','advisor@example.com','0500000002');
INSERT INTO STUDENT VALUES ('H001','CIS','1234');
INSERT INTO ADVISOR VALUES ('A001','1234');
INSERT INTO Login VALUES ('H001','1234','student');
INSERT INTO Login VALUES ('A001','1234','advisor');

INSERT INTO COURSE VALUES ('CIS1103','Introduction to Information Technology',3);
INSERT INTO COURSE VALUES ('CIS1203','Programming Fundamentals (Java)',3);
INSERT INTO COURSE VALUES ('CIS2103','Object-Oriented Programming',3);
INSERT INTO COURSE VALUES ('CIS2203','Database Systems',3);
INSERT INTO COURSE VALUES ('CIS2303','Web Development I',3);
INSERT INTO COURSE VALUES ('CIS3103','Software Engineering',3);
INSERT INTO COURSE VALUES ('CIS3313','Advanced Object-Oriented Programming',3);
INSERT INTO COURSE VALUES ('CIS3403','Mobile Application Development',3);
INSERT INTO COURSE VALUES ('SEC1103','Introduction to Cybersecurity',3);
INSERT INTO COURSE VALUES ('SEC2103','Network Security',3);
INSERT INTO COURSE VALUES ('SEC2203','Ethical Hacking Essentials',3);
INSERT INTO COURSE VALUES ('SEC2303','Digital Forensics',3);
INSERT INTO COURSE VALUES ('SEC2403','Secure Coding Principles',3);
INSERT INTO COURSE VALUES ('SEC3103','Advanced Penetration Testing',3);
INSERT INTO COURSE VALUES ('EDU1103','Foundations of Education',3);
INSERT INTO COURSE VALUES ('EDU1203','Child Development',3);
INSERT INTO COURSE VALUES ('EDU2103','Classroom Management',3);
INSERT INTO COURSE VALUES ('EDU2203','Educational Psychology',3);
INSERT INTO COURSE VALUES ('EDU2303','Curriculum & Instruction',3);
INSERT INTO COURSE VALUES ('EDU3103','Assessment & Evaluation in Education',3);

INSERT INTO REGISTRATION (student_id, course_id, status) VALUES ('H001','CIS1103','Approved');
