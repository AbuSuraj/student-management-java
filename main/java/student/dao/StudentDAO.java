package student.dao;

import student.model.Department;
import student.model.Student; // <-- IMPORT THE CORRECT STUDENT CLASS
import student.util.DatabaseConnection;

import java.sql.*;
import java.util.*;

import static java.util.Collection.*;

// Remove the generic <Student>
public class StudentDAO {

    public StudentDAO() {
        // Default constructor
    }

    /**
     * add student
     */
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (student_number, first_name, last_name, email, phone, date_of_birth, address, department, gpa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Generate student_number automatically if not provided
            String studentNumber = student.getStudentNumber();
            if (studentNumber == null || studentNumber.trim().isEmpty()) {
                studentNumber = generateStudentNumber(student.getDepartment());
            }
            pstmt.setString(1, studentNumber);

            pstmt.setString(2, student.getFirstName());
            pstmt.setString(3, student.getLastName());
            pstmt.setString(4, student.getEmail());

            // Handle potentially null phone
            if (student.getPhone() != null) {
                pstmt.setString(5, student.getPhone());
            } else {
                pstmt.setNull(5, java.sql.Types.VARCHAR);
            }

            // Handle potentially null date_of_birth
            if (student.getDateOfBirth() != null) {
                pstmt.setDate(6, java.sql.Date.valueOf(student.getDateOfBirth()));
            } else {
                pstmt.setNull(6, java.sql.Types.DATE);
            }

            // Handle potentially null address
            if (student.getAddress() != null) {
                pstmt.setString(7, student.getAddress());
            } else {
                pstmt.setNull(7, java.sql.Types.VARCHAR);
            }

            // Handle department - store as string (you can store either fullName or code)
            if (student.getDepartment() != null) {
                pstmt.setString(8, student.getDepartment().getFullName()); // or use getCode() if you prefer
            } else {
                pstmt.setNull(8, java.sql.Types.VARCHAR);
            }

            pstmt.setDouble(9, student.getGpa());

            pstmt.executeUpdate();
            System.out.println("✅ Student added successfully");

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to generate student number
    private String generateStudentNumber(Department department) {
        String year = String.valueOf(java.time.Year.now().getValue());
        String deptCode = department != null ? department.getCode() : "GEN";

        // Generate a random 4-digit number or use a sequence
        int randomNum = (int) (Math.random() * 9000) + 1000;

        return year + deptCode + randomNum;
    }
//    public void addStudent(Student student) {
//        String sql = "INSERT INTO students (first_name, last_name, email, address, gpa) VALUES (?, ?, ?, ?, ?)";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, student.getFirstName());
//            pstmt.setString(2, student.getLastName());
//            pstmt.setString(3, student.getEmail());
//
//            // Handle potentially null address
//            if (student.getAddress() != null) {
//                pstmt.setString(4, student.getAddress());
//            } else {
//                pstmt.setNull(4, java.sql.Types.VARCHAR);
//            }
//
//            pstmt.setDouble(5, student.getGpa());
//
//            pstmt.executeUpdate();
//            System.out.println("✅ Student added successfully");
//
//
//        } catch (SQLException e) {
//            System.out.println("Error adding student: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }


    /**
     * fetch student.
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getLong("student_id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));

                student.setGpa(resultSet.getDouble("gpa"));

                students.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    /**
     * update student
     */
    public void updateStudent(long id, double newGpa) {
        String sql = "UPDATE students SET gpa = ? WHERE student_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(2, id);
            pstmt.setDouble(1, newGpa);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student GPA updated");
            } else {
                System.out.println("⚠️ No student found with id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error updating student: " + e.getMessage());
        }
    }

    public void deleteStudent(long id) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student deleted");
            } else {
                System.out.println("⚠️ No student found with id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error deleting student: " + e.getMessage());
        }
    }

    public Student getStudentById(long id) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)
        ) {

            pstmt.setLong(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getLong("student_id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));
                student.setGpa(resultSet.getDouble("gpa"));
                return student;
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching student: " + e.getMessage());
        }
        return null;
    }

    public void sortStudentByGpa() {
        List<Student> students = getAllStudents();
       System.out.println("sorting students by GPA");
        /**
         * mordern way to sort
         *
         students.sort(Comparator.comparingDouble(Student::getGpa));
         students.forEach(System.out::println);
         */

        /**
         * using lamda expression to sort.
         * */

        students.sort((s1,s2)->Double.compare(s1.getGpa(), s2.getGpa()));
        students.forEach(System.out::println);
//        students.sort((s1,s2)->Double.compare((s2.getGpa()), s1.getGpa()));
//        students.forEach(System.out::println);
        /** Old way
         *
         *         Collections.sort(students, new Comparator<Student>() {
         *             @Override
         *             public int compare(Student o1, Student o2) {
         *                 return Double.compare(o1.getGpa(), o2.getGpa());
         *             }
         *         });
         *         students.forEach(System.out::println);
         */

    }

   public void sortStudentByStudentId() {
        List<Student> students = getAllStudents();
        System.out.println("sorting students by studentId");
        students.sort(Comparator.comparingLong(Student::getStudentId));
        students.forEach(System.out::println);
    }
public void getUniqueName(){
  List<Student> students = getAllStudents();
  Set<String> uniqueNames = new HashSet<>();
  for (Student student : students) {
      uniqueNames.add(student.getFirstName() + " " + student.getLastName());

  }
  System.out.println(uniqueNames);
}

public Map<Long, Student> getStudentMap() {
        List<Student> students = getAllStudents();
        Map<Long, Student> studentMap = new HashMap<>();
        for (Student student : students) {
            studentMap.put(student.getStudentId(), student);
        }
        return studentMap;
}

public Map<Double, Integer> getGPAFrequency() {
        List<Student> students = getAllStudents();

        Map<Double, Integer> studentMap = new HashMap<>();
        for(Student student: students){
            double gpa = student.getGpa();
            studentMap.put( gpa, studentMap.getOrDefault(gpa, 0) +1 );
        }
        return studentMap;
}

}