package student.main;

import student.dao.StudentDAO;
import student.model.Student;

import java.util.Map;

public class StudentCollectionTest {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        dao.getUniqueName();
        Map<Long, Student> studentMap = dao.getStudentMap();

        System.out.println(studentMap);

        Map<Double, Integer> gpaFreq = dao.getGPAFrequency();
        System.out.println("gpa freq");
        System.out.println(gpaFreq);
    }
}