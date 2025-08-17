package student.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Student Management System Starting...");

        // Test database connection
        String url = "jdbc:postgresql://localhost:5432/student_management_db";
        String username = "postgres";  // Your PostgreSQL username
        String password = "postgres";  // Replace with your actual password

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Database connection successful!");

            // Test query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as student_count FROM students");

            if (rs.next()) {
                System.out.println("📊 Total students in database: " + rs.getInt("student_count"));
            }

            // Show sample student data
            ResultSet rs2 = stmt.executeQuery("SELECT student_number, first_name, last_name, department FROM students LIMIT 3");
            System.out.println("\n📋 Sample Students:");
            while (rs2.next()) {
                System.out.printf("  %s - %s %s (%s)%n",
                        rs2.getString("student_number"),
                        rs2.getString("first_name"),
                        rs2.getString("last_name"),
                        rs2.getString("department")
                );
            }

            rs.close();
            rs2.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

//package student.main;
//
//import student.dao.StudentDAO;
//import student.model.Student; // <-- IMPORT THE CORRECT STUDENT CLASS
//
//public class Main {
//    public static void main(String[] args) {
//        // Create a DAO instance
//        StudentDAO studentDAO = new StudentDAO();
//
//        // Create a new Student object with actual data
//        System.out.println("Creating a new student...");
//        Student newStudent = new Student("sara", "ssdaara@gamil.com");
//
//        studentDAO.addStudent(newStudent);
//
//        System.out.println("Student added successfully!");
//        System.out.println("Please check your database to verify.");
//    }
//}