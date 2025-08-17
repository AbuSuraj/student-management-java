package student.dao;

import student.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadedStudentManger {
    private StudentDAO studentDAO;
    private ExecutorService executorService;

    public ThreadedStudentManger(){
        this.studentDAO = new StudentDAO();
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void bulkInsertStudent(List<Student> students){
        System.out.println("BULK INSERT STUDENT");

        List<Future<Void>> futures = new ArrayList<>();

        for(Student student : students){
            Future<Void> future = executorService.submit(() ->{
               try{
                   System.out.println("Thread "+ Thread.currentThread().getName() + "inserting: "+ student.getFullName());
                   studentDAO.addStudent(student);
                   Thread.sleep(1000);
               } catch (Exception e){
                   System.err.println("Error in bulk insert student"+ e.getMessage());
               }
               return null;
            });
            futures.add(future);
        }
        // Wait for all threads to complete
        for (Future<Void> future : futures) {
            try {
                future.get(); // This blocks until the thread completes
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("✅ All students inserted!");
    }
}
