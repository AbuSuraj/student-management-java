package student.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    public enum Semester {
        SPRING("Spring"),
        SUMMER("Summer"),
        FALL("Fall"),
        WINTER("Winter");

        private final String displayName;

        Semester(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    // Private fields
    private Long courseId;
    private String courseCode;
    private String courseName;
    private String description;
    private int credits;
    private Department department;
    private Semester semester;
    private int academicYear;
    private int maxStudents;
    private String teacherName;
    private boolean isActive;

    // Default constructor
    public Course() {
        this.isActive = true;
        this.credits = 3; // Default credits
        this.maxStudents = 50; // Default max students
    }

    // Constructor with essential fields
    public Course(String courseCode, String courseName, int credits,
                  Department department, Semester semester, int academicYear) {
        this();
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    // Full constructor
    public Course(Long courseId, String courseCode, String courseName,
                  String description, int credits, Department department,
                  Semester semester, int academicYear, int maxStudents,
                  String teacherName, boolean isActive) {
        this();
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        this.department = department;
        this.semester = semester;
        this.academicYear = academicYear;
        this.maxStudents = maxStudents;
        this.teacherName = teacherName;
        this.isActive = isActive;
    }

    // Getters and Setters with validation
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty");
        }
        this.courseCode = courseCode.trim().toUpperCase();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty");
        }
        this.courseName = courseName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits < 1 || credits > 6) {
            throw new IllegalArgumentException("Credits must be between 1 and 6");
        }
        this.credits = credits;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        if (academicYear < 2020 || academicYear > 2030) {
            throw new IllegalArgumentException("Academic year must be between 2020 and 2030");
        }
        this.academicYear = academicYear;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        if (maxStudents < 1) {
            throw new IllegalArgumentException("Max students must be at least 1");
        }
        this.maxStudents = maxStudents;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Business methods
    public String getFullCourseTitle() {
        return courseCode + " - " + courseName;
    }

    public String getSemesterYear() {
        return semester + " " + academicYear;
    }

    // Override toString
    @Override
    public String toString() {
        return String.format("Course{id=%d, code='%s', name='%s', credits=%d, department=%s, semester=%s %d, teacher='%s'}",
                courseId, courseCode, courseName, credits, department, semester, academicYear, teacherName);
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode) &&
                Objects.equals(semester, course.semester) &&
                Objects.equals(academicYear, course.academicYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, semester, academicYear);
    }
}
    