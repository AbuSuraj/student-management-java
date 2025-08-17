\c
student_management_db;
   -- Create sequences
CREATE SEQUENCE student_seq START 1001;
CREATE SEQUENCE course_seq START 2001;

-- Students table
CREATE TABLE students
(
    student_id      BIGINT PRIMARY KEY           DEFAULT nextval('student_seq'),
    student_number  VARCHAR(20) UNIQUE  NOT NULL,
    first_name      VARCHAR(100)        NOT NULL,
    last_name       VARCHAR(100)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    phone           VARCHAR(20),
    date_of_birth   DATE,
    address         TEXT,
    department      VARCHAR(50)         NOT NULL,
    enrollment_date DATE                NOT NULL DEFAULT CURRENT_DATE,
    gpa             DECIMAL(3, 2)                DEFAULT 0.00,
    is_active       BOOLEAN                      DEFAULT TRUE,
    created_at      TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP
);

-- Courses table
CREATE TABLE courses
(
    course_id     BIGINT PRIMARY KEY          DEFAULT nextval('course_seq'),
    course_code   VARCHAR(20) UNIQUE NOT NULL,
    course_name   VARCHAR(255)       NOT NULL,
    description   TEXT,
    credits       INTEGER            NOT NULL DEFAULT 3,
    department    VARCHAR(50)        NOT NULL,
    semester      VARCHAR(20)        NOT NULL,
    academic_year INTEGER            NOT NULL,
    max_students  INTEGER                     DEFAULT 50,
    teacher_name  VARCHAR(255),
    is_active     BOOLEAN                     DEFAULT TRUE,
    created_at    TIMESTAMP                   DEFAULT CURRENT_TIMESTAMP
);

-- Enrollments table
CREATE TABLE enrollments
(
    enrollment_id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    student_id      BIGINT NOT NULL,
    course_id       BIGINT NOT NULL,
    enrollment_date DATE   NOT NULL DEFAULT CURRENT_DATE,
    status          VARCHAR(20)     DEFAULT 'ENROLLED',
    FOREIGN KEY (student_id) REFERENCES students (student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE,
    UNIQUE (student_id, course_id)
);

-- Grades table
CREATE TABLE grades
(
    grade_id        BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    enrollment_id   BIGINT        NOT NULL,
    assignment_name VARCHAR(255)  NOT NULL,
    grade_type      VARCHAR(50)   NOT NULL, -- QUIZ, MIDTERM, FINAL, ASSIGNMENT
    points_earned   DECIMAL(5, 2) NOT NULL,
    total_points    DECIMAL(5, 2) NOT NULL,
    percentage      DECIMAL(5, 2) GENERATED ALWAYS AS (points_earned * 100.0 / total_points) STORED,
    grade_date      DATE          NOT NULL DEFAULT CURRENT_DATE,
    remarks         TEXT,
    FOREIGN KEY (enrollment_id) REFERENCES enrollments (enrollment_id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_student_email ON students (email);
CREATE INDEX idx_student_department ON students (department);
CREATE INDEX idx_course_code ON courses (course_code);
CREATE INDEX idx_course_department ON courses (department);
CREATE INDEX idx_enrollment_student ON enrollments (student_id);
CREATE INDEX idx_enrollment_course ON enrollments (course_id);
CREATE INDEX idx_grade_enrollment ON grades (enrollment_id);

-- Insert sample data
INSERT INTO students (student_number, first_name, last_name, email, phone, date_of_birth, department, enrollment_date)
VALUES ('STU1001', 'John', 'Doe', 'john.doe@university.edu', '123-456-7890', '2002-05-15', 'COMPUTER_SCIENCE',
        '2023-09-01'),
       ('STU1002', 'Jane', 'Smith', 'jane.smith@university.edu', '987-654-3210', '2001-08-22', 'COMPUTER_SCIENCE',
        '2023-09-01'),
       ('STU1003', 'Mike', 'Johnson', 'mike.johnson@university.edu', '555-123-4567', '2002-12-10', 'MATHEMATICS',
        '2023-09-01'),
       ('STU1004', 'Sarah', 'Wilson', 'sarah.wilson@university.edu', '444-987-6543', '2003-03-07', 'PHYSICS',
        '2023-09-01');

INSERT INTO courses (course_code, course_name, description, credits, department, semester, academic_year, teacher_name)
VALUES ('CS101', 'Introduction to Programming', 'Basic programming concepts using Java', 4, 'COMPUTER_SCIENCE', 'FALL',
        2024, 'Dr. Alice Brown'),
       ('CS201', 'Data Structures', 'Advanced data structures and algorithms', 4, 'COMPUTER_SCIENCE', 'SPRING', 2024,
        'Dr. Bob Davis'),
       ('MATH101', 'Calculus I', 'Differential and integral calculus', 4, 'MATHEMATICS', 'FALL', 2024,
        'Dr. Carol Miller'),
       ('PHY101', 'Physics I', 'Mechanics and thermodynamics', 4, 'PHYSICS', 'FALL', 2024, 'Dr. David Lee');

-- Sample enrollments
INSERT INTO enrollments (student_id, course_id)
VALUES (1001, 2001),
       (1001, 2003),
       (1002, 2001),
       (1002, 2002),
       (1003, 2003),
       (1003, 2001),
       (1004, 2004),
       (1004, 2003);

-- Sample grades
INSERT INTO grades (enrollment_id, assignment_name, grade_type, points_earned, total_points, remarks)
VALUES (1, 'Quiz 1', 'QUIZ', 18.5, 20, 'Good work'),
       (1, 'Midterm Exam', 'MIDTERM', 85, 100, 'Excellent understanding'),
       (2, 'Quiz 1', 'QUIZ', 16, 20, 'Need improvement'),
       (3, 'Assignment 1', 'ASSIGNMENT', 95, 100, 'Outstanding work'),
       (4, 'Quiz 1', 'QUIZ', 17, 20, 'Good effort');