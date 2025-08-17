package student.dao;

import student.model.Course;
import student.model.Department;
import student.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public void addCourse(Course course) {
        String sql =  "INSERT INTO courses (course_code, course_name, description, credits, department, semester, academic_year, max_students, teacher_name, is_active) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, course.getCourseCode());
            pstmt.setString(2, course.getCourseName());
            pstmt.setString(3, course.getDescription());
            pstmt.setInt(4, course.getCredits());
            pstmt.setString(5, course.getDepartment().name());
            pstmt.setString(6, course.getSemester().name());
            pstmt.setInt(7, course.getAcademicYear());
            pstmt.setInt(8, course.getMaxStudents());
            pstmt.setString(9, course.getTeacherName());
            pstmt.setBoolean(10, course.isActive());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error adding course: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
   public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

       try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

           while (rs.next()) {
               Course course = new Course();
               course.setCourseId(rs.getLong("id"));
               course.setCourseCode(rs.getString("course_code"));
               course.setCourseName(rs.getString("course_name"));
               course.setDescription(rs.getString("description"));
               course.setCredits(rs.getInt("credits"));
               course.setDepartment(Department.valueOf(rs.getString("department")));
               course.setSemester(Course.Semester.valueOf(rs.getString("semester")));
               course.setAcademicYear(rs.getInt("academic_year"));
               course.setMaxStudents(rs.getInt("max_students"));
               course.setTeacherName(rs.getString("teacher_name"));
               course.setActive(rs.getBoolean("is_active"));

               courses.add(course);
           }

       } catch (SQLException e) {
           System.out.println("❌ Error fetching courses: " + e.getMessage());
       }

       return courses;
   }
}
