CREATE DATABASE student_db;
USE student_db;

CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    date_of_birth DATE,
    gender ENUM('Male', 'Female', 'Other'),
    address TEXT
);

CREATE TABLE Courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    course_code VARCHAR(10) UNIQUE NOT NULL,
    credits INT NOT NULL
);

CREATE TABLE Departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL
);

ALTER TABLE Students
ADD COLUMN department_id INT,
ADD FOREIGN KEY (department_id) REFERENCES Departments(department_id);

SHOW TABLES;
SHOW DATABASES;

INSERT INTO Students(student_id, first_name, last_name, email) VALUES (100, 'Jesse', 'Mokolo', 'jessemokolo@gmail.com');
SHOW TABLES FROM student_db;

SELECT * FROM Students;

INSERT INTO Students(student_id, first_name, last_name, email) VALUES (101, 'Aneminyene', 'Jimmy', 'aneminyenejimmy@gmail.com');

INSERT INTO Departments(department_id, department_name) VALUES (1, 'Software Engineering');
INSERT INTO Departments(department_id, department_name) VALUES (2, 'Computer Science');
INSERT INTO Departments(department_id, department_name) VALUES (3, 'Information Technology');
