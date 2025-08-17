package student.model;

import java.time.LocalDate;

public class EnrollMent {

    //Enrollment status enum
    public enum EnrollmentStatus {
        ENROLLED("Enrolled"),
        DROPPED("Dropped"),
        COMPLETED("Completed"),
        WITHDRAWN("Withdrawn");

        private  final String displayName;

        EnrollmentStatus(String displayName) {
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

    private  Long enrollmentId;
    private  Long studentId;
    private Long courseId;
    private EnrollmentStatus status;
    private LocalDate enrollmentDate;


}
