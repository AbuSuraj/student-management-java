package student.main;

import student.dao.ThreadedStudentManger;
import student.model.Department;
import student.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ThreadPracticingMain {
    public static void main(String[] args) {
        ThreadedStudentManger manager = new ThreadedStudentManger();

        // create sample students

        List<Student> students =  createSampleStudents();
        System.out.println("=== Threading Practice Examples ===\n");

        // Example 1: Bulk insert with threads
        manager.bulkInsertStudent(students.subList(0, 5));

        System.out.println("\n" + String.join("", java.util.Collections.nCopies(50, "=")) + "\n");

    }

    private static List<Student> createSampleStudents() {
        List<Student> students = new ArrayList<>();
        String[] firstNames = {"Bli", "Jarina", "Kamaran", "Zerin", "HUssain",
                "Hasib", "Omar Faruk", "Nadia", "Tariq", "Sara"};
        String[] lastNames = {"Khan", "Sheikh", "Rahman", "Ahmed", "Ali"};
        Department[] departments = Department.values();

        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setFirstName(firstNames[i % firstNames.length]);
            student.setLastName(lastNames[i % lastNames.length]);
            student.setEmail(firstNames[i % firstNames.length].toLowerCase() +
                    (i + 1)  + "@buet.edu");
            student.setDepartment(departments[i % departments.length]);
            student.setGpa(2.5 + Math.random() * 1.5); // GPA between 2.5-4.0
            students.add(student);
        }

        return students;
    }
}
