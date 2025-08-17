package student.main;

import student.dao.StudentDAO;
import student.model.Department;
import student.model.Student;

import java.util.List;
import java.util.Map;

public class JdbcStudentTest {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // 1. Add Student - using simplified constructor or default constructor
//        Student student1 = new Student();
//        student1.setFirstName("Shwara");
//        student1.setLastName("Khan");
//        student1.setEmail("a1@gmail.edu");
//        student1.setAddress("Asdt, Sylhet");
//        student1.setGpa(3.7);
//
//        Student student2 = new Student();
//        student2.setFirstName("Rahim");
//        student2.setLastName("Uddin");
//        student2.setEmail("rahim.uddin@gmail.edu");
//        student2.setAddress("Dhanmondi, Dhaka");
//        student2.setGpa(3.2);
//
//        Student student3 = new Student();
//        student3.setFirstName("Karina");
//        student3.setLastName("Akter");
//        student3.setEmail("karina.ak@gmail.edu");
//        student3.setAddress("Chawkbazar, Chittagong");
//        student3.setGpa(3.9);
//
//        Student student4 = new Student();
//        student4.setFirstName("Nayeem");
//        student4.setLastName("Hasan");
//        student4.setEmail("nayeem.hasan@gmail.edu");
//        student4.setAddress("Uposhohor, Sylhet");
//        student4.setGpa(3.5);
//
//        Student student5 = new Student();
//        student5.setFirstName("Fatima");
//        student5.setLastName("Begum");
//        student5.setEmail("fatima.begum@gmail.edu");
//        student5.setAddress("Rajshahi Sadar, Rajshahi");
//        student5.setGpa(3.8);
//
//        Student student6 = new Student();
//        student6.setFirstName("Tariq");
//        student6.setLastName("Mahmud");
//        student6.setEmail("tariq.mahmud@gmail.edu");
//        student6.setAddress("Zindabazar, Sylhet");
//        student6.setGpa(2.9);
//
//        Student student7 = new Student();
//        student7.setFirstName("Rima");
//        student7.setLastName("Islam");
//        student7.setEmail("rima.islam@gmail.edu");
//        student7.setAddress("Bashundhara, Dhaka");
//        student7.setGpa(3.4);
//
//        Student student8 = new Student();
//        student8.setFirstName("Sajib");
//        student8.setLastName("Chowdhury");
//        student8.setEmail("sajib.c@gmail.edu");
//        student8.setAddress("Agrabad, Chittagong");
//        student8.setGpa(3.1);
//
//        Student student9 = new Student();
//        student9.setFirstName("Mehzabin");
//        student9.setLastName("Rahman");
//        student9.setEmail("mehzabin.rahman@gmail.edu");
//        student9.setAddress("Panchlaish, Chittagong");
//        student9.setGpa(3.6);
//
//        Student student10 = new Student();
//        student10.setFirstName("Tanvir");
//        student10.setLastName("Alam");
//        student10.setEmail("tanvir.aslams@gmail.edu");
//        student10.setAddress("Kushtia Sadar, Kushtia");
//        student10.setGpa(3.3);
// 1. Add Students
        // 1. Add Student - using simplified constructor or default constructor
        Student student1 = new Student();
        student1.setFirstName("Hsdwasd");
        student1.setLastName("Khan");
        student1.setEmail("a1sd@gmail.edu");
        student1.setAddress("Asdt, Sylhet");
        student1.setGpa(3.7);
        student1.setDepartment(Department.COMPUTER_SCIENCE);
//
//        Student student2 = new Student();
//        student2.setFirstName("Rahim");
//        student2.setLastName("Uddin");
//        student2.setEmail("rahim.uddin@gmail.edu");
//        student2.setAddress("Dhanmondi, Dhaka");
//        student2.setGpa(3.2);
//        student2.setDepartment(Department.BUSINESS_ADMINISTRATION);
//
//        Student student3 = new Student();
//        student3.setFirstName("Karina");
//        student3.setLastName("Akter");
//        student3.setEmail("karina.ak@gmail.edu");
//        student3.setAddress("Chawkbazar, Chittagong");
//        student3.setGpa(3.9);
//        student3.setDepartment(Department.ENGLISH);
//
//        Student student4 = new Student();
//        student4.setFirstName("Nayeem");
//        student4.setLastName("Hasan");
//        student4.setEmail("nayeem.hasan@gmail.edu");
//        student4.setAddress("Uposhohor, Sylhet");
//        student4.setGpa(3.5);
//        student4.setDepartment(Department.MATHMATICS);
//
//        Student student5 = new Student();
//        student5.setFirstName("Fatima");
//        student5.setLastName("Begum");
//        student5.setEmail("fatima.begum@gmail.edu");
//        student5.setAddress("Rajshahi Sadar, Rajshahi");
//        student5.setGpa(3.8);
//        student5.setDepartment(Department.PHYSICS);
//
//        Student student6 = new Student();
//        student6.setFirstName("Tariq");
//        student6.setLastName("Mahmud");
//        student6.setEmail("tariq.mahmud@gmail.edu");
//        student6.setAddress("Zindabazar, Sylhet");
//        student6.setGpa(2.9);
//        student6.setDepartment(Department.CHEMISTRY);
//
//        Student student7 = new Student();
//        student7.setFirstName("Rima");
//        student7.setLastName("Islam");
//        student7.setEmail("rima.islam@gmail.edu");
//        student7.setAddress("Bashundhara, Dhaka");
//        student7.setGpa(3.4);
//        student7.setDepartment(Department.BIOLOGY);
//
//        Student student8 = new Student();
//        student8.setFirstName("Sajib");
//        student8.setLastName("Chowdhury");
//        student8.setEmail("sajib.c@gmail.edu");
//        student8.setAddress("Agrabad, Chittagong");
//        student8.setGpa(3.1);
//        student8.setDepartment(Department.ELECTRICAL_ENGINEERING);
//
//        Student student9 = new Student();
//        student9.setFirstName("Mehzabin");
//        student9.setLastName("Rahman");
//        student9.setEmail("mehzabin.rahman@gmail.edu");
//        student9.setAddress("Panchlaish, Chittagong");
//        student9.setGpa(3.6);
//        student9.setDepartment(Department.MECHANICAL_ENGINEERING);
//
//        Student student10 = new Student();
//        student10.setFirstName("Tanvir");
//        student10.setLastName("Alam");
//        student10.setEmail("tanvir.aslams@gmail.edu");
//        student10.setAddress("Kushtia Sadar, Kushtia");
//        student10.setGpa(3.3);
//        student10.setDepartment(Department.COMPUTER_SCIENCE);



//        dao.addStudent(student1);
//        dao.addStudent(student2);
//        dao.addStudent(student3);
//        dao.addStudent(student4);
//        dao.addStudent(student5);
//        dao.addStudent(student6);
//        dao.addStudent(student7);
//        dao.addStudent(student8);
//        dao.addStudent(student9);
//        dao.addStudent(student10);
//        2. fetch all students.
        List<Student> students = dao.getAllStudents();
        for (Student s : students) {
            System.out.println(s);
        }

        // 3. update
//        dao.updateStudent(3, 3.44);
//        // 4. fetch by id
//      Student student1 =  dao.getStudentById(3);
//      System.out.println("Fetched " +student1);
// 5. Delete
//        dao.deleteStudent(1);
//        dao.sortStudentByGpa();
//        dao.sortStudentByStudentId();

    }

}