package student.main;

import student.model.Department;
import student.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class to demonstrate OOP concepts and Collections usage
 * This replaces your database test for now
 */
public class StudentTest {

    public static void main(String[] args) {
        System.out.println("=== Student Management System - Phase 1 Test ===\n");

        // Test 1: Creating Student objects (OOP - Constructors)
        testStudentCreation();

        // Test 2: Using Collections with Students
        testCollectionsUsage();

        // Test 3: Enum usage
        testEnumUsage();

        // Test 4: Validation and Exception handling
        testValidation();
    }

    private static void testStudentCreation() {
        System.out.println("1. Testing Student Creation (OOP - Constructors, Encapsulation)");
        System.out.println("================================================================");

        // Using different constructors
        Student student1 = new Student("STU00112", "Abu", "Suraj",
                "abu.suraj@university.edu",
                Department.COMPUTER_SCIENCE);

        Student student2 = new Student();
        student2.setStudentNumber("STU002");
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setEmail("jane.smith@university.edu");
        student2.setDepartment(Department.MATHMATICS);
        student2.setDateOfBirth(LocalDate.of(2002, 5, 15));
        student2.setGpa(3.75);

        // Test toString method
        System.out.println("Student 1: " + student1);
        System.out.println("Student 2: " + student2);

        // Test business methods
        System.out.println("Student 1 Full Name: " + student1.getFullName());
        System.out.println("Student 2 Age: " + student2.getAge());
        System.out.println();
    }

    private static void testCollectionsUsage() {
        System.out.println("2. Testing Collections Framework");
        System.out.println("=================================");

        // ArrayList usage
        List<Student> studentList = new ArrayList<>();

        // Add students to list
        studentList.add(new Student("STU001", "John", "Doe", "john@uni.edu", Department.COMPUTER_SCIENCE));
        studentList.add(new Student("STU002", "Jane", "Smith", "jane@uni.edu", Department.MATHMATICS));
        studentList.add(new Student("STU003", "Mike", "Johnson", "mike@uni.edu", Department.PHYSICS));
        studentList.add(new Student("STU004", "Sarah", "Wilson", "sarah@uni.edu", Department.COMPUTER_SCIENCE));

        // Set GPAs
        studentList.get(0).setGpa(3.5);
        studentList.get(1).setGpa(3.8);
        studentList.get(2).setGpa(3.2);
        studentList.get(3).setGpa(3.9);

        System.out.println("Total students: " + studentList.size());

        // HashMap usage - Student lookup by student number
        Map<String, Student> studentMap = new HashMap<>();
        for (Student student : studentList) {
            studentMap.put(student.getStudentNumber(), student);
        }

        // Lookup student
        Student foundStudent = studentMap.get("STU002");
        System.out.println("Found student: " + foundStudent.getFullName());

        // Filter students by department (basic iteration)
        System.out.println("\nComputer Science Students:");
        for (Student student : studentList) {
            if (student.getDepartment() == Department.COMPUTER_SCIENCE) {
                System.out.println("  - " + student.getFullName() + " (GPA: " + student.getGpa() + ")");
            }
        }

        // Find highest GPA
        double highestGpa = 0.0;
        Student topStudent = null;
        for (Student student : studentList) {
            if (student.getGpa() > highestGpa) {
                highestGpa = student.getGpa();
                topStudent = student;
            }
        }
        System.out.println("\nTop Student: " + topStudent.getFullName() + " (GPA: " + highestGpa + ")");
        System.out.println();
    }

    private static void testEnumUsage() {
        System.out.println("3. Testing Enum Usage");
        System.out.println("=====================");

        // Display all departments
        System.out.println("Available Departments:");
        for (Department dept : Department.values()) {
            System.out.println("  - " + dept + " | STEM: " + dept.isSTEM());
        }

        // Test enum methods
        Department cs = Department.fromCode("CS");
        System.out.println("\nDepartment from code 'CS': " + cs);

        Department math = Department.fromFullName("Mathematics");
        System.out.println("Department from name 'Mathematics': " + math);
        System.out.println();
    }

    private static void testValidation() {
        System.out.println("4. Testing Validation (Exception Handling Preview)");
        System.out.println("===================================================");

        try {
            Student invalidStudent = new Student();
            invalidStudent.setFirstName(""); // This should throw an exception
            System.out.println("This line should not be reached");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Caught expected exception: " + e.getMessage());
        }

        try {
            Student student = new Student();
            student.setGpa(5.0); // Invalid GPA
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Caught expected exception: " + e.getMessage());
        }

        try {
            Student student = new Student();
            student.setEmail("invalid-email"); // Invalid email
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Caught expected exception: " + e.getMessage());
        }

        System.out.println("\n✅ All tests completed successfully!");
    }
}